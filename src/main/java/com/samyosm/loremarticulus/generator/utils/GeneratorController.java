package com.samyosm.loremarticulus.generator.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.samyosm.loremarticulus.repository.UserRepo;
import com.samyosm.loremarticulus.utils.ScriptEvaluator;

import java.io.IOException;

import static com.samyosm.loremarticulus.utils.UrlReader.ReadUrl;
public class GeneratorController{

    private final Generator generator;

    public GeneratorController(UserRepo userRepo, String apiKey) {
        generator = new Generator(userRepo, apiKey);
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

        return generator.Generate(query, authToken.replace("Bearer ", ""));

    }
}
