package com.samyosm.loremarticulus.utils;

import com.google.gson.Gson;
import com.samyosm.loremarticulus.models.UserItem;
import com.samyosm.loremarticulus.objects.gptcompletion.GPTCompletionRequest;
import com.samyosm.loremarticulus.objects.gptcompletion.GPTCompletionResponse;
import com.samyosm.loremarticulus.repositories.UserRepository;
import kong.unirest.Unirest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.samyosm.loremarticulus.config.GeneratorConfig.API_URL;

public class TextFetcher {

    private final UserRepository userRepository;
    private final String openaiApiKey;

    public TextFetcher(UserRepository userRepository, String openaiApiKey) {
        this.userRepository = userRepository;
        this.openaiApiKey = openaiApiKey;
    }

    private UserItem getUser(String uidToken) {
        var temp = uidToken.split("-");
        if (temp.length != 2) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        var uid = temp[0];
        var token = temp[1];

        if (!userRepository.existsById(uid)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        var user = userRepository.findUserItemById(uid);

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
        userRepository.save(user);
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
