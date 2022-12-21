package com.samyosm.loremarticulus.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.samyosm.loremarticulus.generator.utils.GeneratorController;

import com.samyosm.loremarticulus.repository.UserRepo;
import kong.unirest.json.JSONObject;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static com.samyosm.loremarticulus.utils.UrlReader.ReadUrl;

@RestController
@RequestMapping("/api/v1/")
public class GeneratorsControllers {
    @Value("${generators.links}")
    private String SIMPLE_GENERATORS_LINK;
    private final GeneratorController generator;

    @Autowired
    public GeneratorsControllers(UserRepo userRepo, @Value("${tokens.openai}") String apiKey) {
        this.generator = new GeneratorController(userRepo, apiKey);
    }

    @PostMapping("{type}")
    public String Generator(@PathVariable String type, @RequestBody JsonNode hints, @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) throws IOException {
        var rawLinks = ReadUrl(SIMPLE_GENERATORS_LINK);
        var links = new JSONObject(rawLinks);
        if (!links.has(type))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "/" + type + "doesn't exists.");
        var script = links.getString(type);
        return generator.generate(hints, authToken, script);
    }
}
