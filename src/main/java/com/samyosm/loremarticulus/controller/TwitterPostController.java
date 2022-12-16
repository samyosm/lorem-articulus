package com.samyosm.loremarticulus.controller;

import com.samyosm.loremarticulus.generator.TwitterPostGenerator;
import com.samyosm.loremarticulus.model.TwitterPostHints;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/twitter")
public class TwitterPostController {

        @Value("${tokens.openai}")
        private String apiKey;

        @PostMapping
        public String GenerateTwitterPost(@RequestBody TwitterPostHints hints) {
            return new TwitterPostGenerator().Generate(hints, apiKey);
        }

}
