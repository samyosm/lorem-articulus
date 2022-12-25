package com.samyosm.loremarticulus.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.samyosm.loremarticulus.repositories.UserRepository;
import com.samyosm.loremarticulus.services.UserService;

import java.io.IOException;

import static com.samyosm.loremarticulus.utils.UrlReader.ReadUrl;

public class Generator {

    private final TextFetcher textFetcher;

    public Generator(UserService userService, String apiKey) {
        textFetcher = new TextFetcher(userService, apiKey);
    }

    private String evaluateQuery(String script, String rawJSON) {
        var evaluator = new ScriptEvaluator();
        var params = evaluator.parseJson(rawJSON);
        var result = evaluator.evaluateString(script, "makeQuery", params);
        evaluator.close();

        return result;
    }

    public String generate(JsonNode rawHints, String authToken, String script) throws IOException {
        var rawJSON = rawHints.toString();
        var query = evaluateQuery(script, rawJSON);

        return textFetcher.write(query, authToken.replace("Bearer ", ""));

    }
}
