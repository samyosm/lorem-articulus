package com.samyosm.loremarticulus.generator.utils;

import com.google.gson.Gson;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionRequest;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionResponse;
import com.samyosm.loremarticulus.repository.UserRepo;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;

import static com.samyosm.loremarticulus.generator.utils.GeneratorConfig.*;

@RequiredArgsConstructor
public class Generator<T> {

    private final UserRepo userRepo;
    private final String openai_api_key;
    private final Query<T> queryMaker;

    public String Generate(T hints, String token) {
        String query = queryMaker.MakeQuery(hints);

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
