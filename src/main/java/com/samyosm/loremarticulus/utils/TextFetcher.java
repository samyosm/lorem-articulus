package com.samyosm.loremarticulus.utils;

import com.google.gson.Gson;
import com.samyosm.loremarticulus.objects.gptcompletion.GPTCompletionRequest;
import com.samyosm.loremarticulus.objects.gptcompletion.GPTCompletionResponse;
import com.samyosm.loremarticulus.services.UserService;
import kong.unirest.Unirest;

import static com.samyosm.loremarticulus.config.GeneratorConfig.API_URL;

public class TextFetcher {

    private final UserService userService;
    private final String openaiApiKey;

    public TextFetcher(UserService userService, String openaiApiKey) {
        this.userService = userService;
        this.openaiApiKey = openaiApiKey;
    }

    private String fetchGPTRequest(String query) {
        var body = new GPTCompletionRequest(query);

        var gson = new Gson();
        var bodyJson = gson.toJson(body);

        var rawResponse = Unirest.post(API_URL)
                .header("Authorization", "Bearer " + openaiApiKey)
                .header("Content-Type", "application/json")
                .body(bodyJson)
                .asJson();
        var response = gson.fromJson(rawResponse.getBody().toString(), GPTCompletionResponse.class);
        return response.choices.get(0).getText();
    }

    public String write(String query, String token) {
        userService.increaseUserUsage(token);

        return fetchGPTRequest(query);
    }
}
