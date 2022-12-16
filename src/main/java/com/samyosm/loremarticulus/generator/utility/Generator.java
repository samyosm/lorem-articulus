package com.samyosm.loremarticulus.generator.utility;

import com.google.gson.Gson;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionRequest;
import com.samyosm.loremarticulus.model.gptcompletion.GPTCompletionResponse;
import kong.unirest.Unirest;

import static com.samyosm.loremarticulus.generator.utility.GeneratorConfig.*;

public interface Generator<T> {

    String MakeQuery(T obj);
    default String Generate(T obj, String token) {
        String query = MakeQuery(obj);

        var requestBodyClass = new GPTCompletionRequest(query);

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
