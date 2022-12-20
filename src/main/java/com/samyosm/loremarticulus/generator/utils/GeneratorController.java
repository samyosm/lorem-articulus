package com.samyosm.loremarticulus.generator.utils;

import com.samyosm.loremarticulus.repository.UserRepo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeneratorController {

    private final UserRepo userRepo;
    private final String apiKey;

    public <T> String Generate(T hints, String authToken, Query<T> query) {
        Generator<T> generator = new Generator<>(userRepo, apiKey, query);
        return generator.Generate(hints, authToken.replace("Bearer ", ""));
    }
}
