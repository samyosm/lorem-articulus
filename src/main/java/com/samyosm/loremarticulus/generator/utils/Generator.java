package com.samyosm.loremarticulus.generator.utils;

import com.google.gson.Gson;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionRequest;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionResponse;
import com.samyosm.loremarticulus.repository.UserRepo;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;

import static com.samyosm.loremarticulus.generator.utils.GeneratorConfig.*;
public class Generator {

    private final UserRepo userRepo;
    private final String openai_api_key;

    public Generator(UserRepo userRepo, String openai_api_key) {
        this.userRepo = userRepo;
        this.openai_api_key = openai_api_key;
    }

    public String Generate(String query, String token) {

        if (!userRepo.existsById(token)) {
            System.out.println(token);
            throw new IllegalArgumentException("Invalid token");
        }

        var user = userRepo.findUserItemByToken(token);

        if (user.getUsage() + 1 >= user.getMaxUsage()) {
            throw new IllegalArgumentException("Too many requests");
        }

        user.setUsage(user.getUsage() + 1);
        userRepo.save(user);

        var body = new GPTCompletionRequest(query);

        var gson = new Gson();
        var bodyJson = gson.toJson(body);

        var rawResponse = Unirest.post(API_URL)
                .header("Authorization", "Bearer " + openai_api_key)
                .header("Content-Type", "application/json")
                .body(bodyJson)
                .asJson();
        var response = gson.fromJson(rawResponse.getBody().toString(), GPTCompletionResponse.class);
        return response.choices.get(0).getText();
    }
}
