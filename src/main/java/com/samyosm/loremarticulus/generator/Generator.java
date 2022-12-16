package com.samyosm.loremarticulus.generator;

import com.google.gson.Gson;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionRequest;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;

import static com.samyosm.loremarticulus.generator.GeneratorConfig.*;

public interface Generator<T> {

    public String MakeQuery(T obj);
    public default String Generate(T obj, String token) {
        System.out.println("Token: " + token);
        String query = MakeQuery(obj);

        var requestBodyClass = new GPTCompletionRequest(query, MAX_TOKEN, TEMPERATURE);

        var gson = new Gson();
        var body = gson.toJson(requestBodyClass);

        var rawResponse = Unirest.post(API_URL)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(body)
                .asJson();
        var response = gson.fromJson(rawResponse.getBody().toString(), GPTCompletionResponse.class);
        return response.choices.get(0).getText();
    }
}
