package com.samyosm.loremarticulus.controller;

import com.samyosm.loremarticulus.generator.YoutubeCommentGenerator;
import com.samyosm.loremarticulus.model.YoutubeCommentHints;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/youtube")
public class YoutubeCommentController {
    @Value("${tokens.openai}")
    private String apiKey;

    @PostMapping
    public String GenerateYoutubeComment(@RequestBody YoutubeCommentHints hints) {
        return new YoutubeCommentGenerator().Generate(hints, apiKey);
    }
}
