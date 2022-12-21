package com.samyosm.loremarticulus.utils;

import com.google.gson.Gson;
import com.samyosm.loremarticulus.models.UserItem;
import com.samyosm.loremarticulus.objects.gptcompletion.GPTCompletionRequest;
import com.samyosm.loremarticulus.objects.gptcompletion.GPTCompletionResponse;
import com.samyosm.loremarticulus.repositories.UserRepo;
import kong.unirest.Unirest;

import static com.samyosm.loremarticulus.config.GeneratorConfig.API_URL;

public class TextWriter {

    private final UserRepo userRepo;
    private final String openaiApiKey;

    public TextWriter(UserRepo userRepo, String openaiApiKey) {
        this.userRepo = userRepo;
        this.openaiApiKey = openaiApiKey;
    }

    private UserItem getUser(String token) {
        if (!userRepo.existsById(token)) {
            throw new IllegalArgumentException("Invalid token");
        }

        var user = userRepo.findUserItemByToken(token);

        if (user.getUsage() + 1 >= user.getMaxUsage()) {
            throw new IllegalArgumentException("Too many requests");
        }

        return user;
    }

    // TODO: Make a UserService with this method
    private void increaseUserUsage(UserItem user) {
        user.setUsage(user.getUsage() + 1);
        userRepo.save(user);
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
        var user = getUser(token);
        increaseUserUsage(user);

        return fetchGPTRequest(query);
    }
}
