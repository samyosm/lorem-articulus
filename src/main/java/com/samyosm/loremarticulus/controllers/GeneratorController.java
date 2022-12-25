package com.samyosm.loremarticulus.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.samyosm.loremarticulus.services.UserService;
import com.samyosm.loremarticulus.utils.Generator;
import com.samyosm.loremarticulus.utils.GeneratorQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.samyosm.loremarticulus.utils.UrlReader.ReadUrl;

@RestController
@RequestMapping("/api/v1/")
public class GeneratorController extends Generator {
    @Value("${generators.links}")
    private String SIMPLE_GENERATORS_LINK;

    @Autowired
    public GeneratorController(UserService userService, @Value("${tokens.openai}") String apiKey) {
        super(userService, apiKey);
    }

    @PostMapping("{type}")
    public String Generator(
            @PathVariable String type,
            @RequestBody JsonNode hints,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken
    ) throws IOException {
        var script = GeneratorQuery.getScript(SIMPLE_GENERATORS_LINK, type);
        return generate(hints, authToken, script);
    }
}
