package com.samyosm.loremarticulus.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.samyosm.loremarticulus.repositories.UserRepository;

import java.io.IOException;

import static com.samyosm.loremarticulus.utils.UrlReader.ReadUrl;

public class Generator {

    private final TextFetcher textFetcher;

    public Generator(UserRepository userRepository, String apiKey) {
        textFetcher = new TextFetcher(userRepository, apiKey);
    }

    private String evaluateQuery(String script, String rawJSON) {
        var evaluator = new ScriptEvaluator();
        var params = evaluator.parseJson(rawJSON);
        var result = evaluator.evaluateString(script, "makeQuery", params);
        evaluator.close();

        return result;
    }

    public String generate(JsonNode rawHints, String authToken, String url) throws IOException {
        var rawJSON = rawHints.toString();
        var script = ReadUrl(url);
        var query = evaluateQuery(script, rawJSON);

        return textFetcher.write(query, authToken.replace("Bearer ", ""));

    }
}
