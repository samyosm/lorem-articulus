package com.samyosm.loremarticulus.generator;

import com.google.gson.Gson;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionRequest;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;

public interface Generator<T> {
    @Value("${token.openai}")
    String OPENAI_BEARER_TOKEN = null;
    int MAX_TOKEN = 3000;
    double TEMPERATURE = 0.1;
    String API_URL = "https://api.openai.com/v1/completions";

    public String MakeQuery(T obj);
    public default String Generate(T obj) {
        String query = MakeQuery(obj);

        var requestBodyClass = new GPTCompletionRequest(query, MAX_TOKEN, TEMPERATURE);

        var gson = new Gson();
        var body = gson.toJson(requestBodyClass);

        var rawResponse = Unirest.post(API_URL)
                .header("Authorization", "Bearer " + OPENAI_BEARER_TOKEN)
                .header("Content-Type", "application/json")
                .body(body)
                .asJson();
        var response = gson.fromJson(rawResponse.getBody().toString(), GPTCompletionResponse.class);
        return response.choices.get(0).getText();
    }
}
