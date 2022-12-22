package com.samyosm.loremarticulus.utils;

import com.google.gson.Gson;
import com.samyosm.loremarticulus.models.UserItem;
import com.samyosm.loremarticulus.objects.gptcompletion.GPTCompletionRequest;
import com.samyosm.loremarticulus.objects.gptcompletion.GPTCompletionResponse;
import com.samyosm.loremarticulus.repositories.UserRepo;
import kong.unirest.Unirest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.samyosm.loremarticulus.config.GeneratorConfig.API_URL;

public class TextFetcher {

    private final UserRepo userRepo;
    private final String openaiApiKey;

    public TextFetcher(UserRepo userRepo, String openaiApiKey) {
        this.userRepo = userRepo;
        this.openaiApiKey = openaiApiKey;
    }

    private UserItem getUser(String uidToken) {
        if (uidToken.length() < 24) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        var uid = uidToken.substring(0, 24);
        var token = uidToken.substring(24);
        if (!userRepo.existsById(uid)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        var user = userRepo.findUserItemById(uid);

        if(!user.getTokens().contains(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        if (user.getUsage() + 1 >= user.getMaxUsage()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usage limit reached");
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
