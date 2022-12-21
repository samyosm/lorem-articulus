package com.samyosm.loremarticulus.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.samyosm.loremarticulus.repositories.UserRepo;

import java.io.IOException;

import static com.samyosm.loremarticulus.utils.UrlReader.ReadUrl;

public class Generator {

    private final TextWriter textWriter;

    public Generator(UserRepo userRepo, String apiKey) {
        textWriter = new TextWriter(userRepo, apiKey);
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

        return textWriter.write(query, authToken.replace("Bearer ", ""));

    }
}
