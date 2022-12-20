package com.samyosm.loremarticulus.controller;

import com.samyosm.loremarticulus.generator.ArticleGenerator;
import com.samyosm.loremarticulus.generator.TwitterPostGenerator;
import com.samyosm.loremarticulus.generator.YoutubeCommentGenerator;
import com.samyosm.loremarticulus.generator.utils.GeneratorController;
import com.samyosm.loremarticulus.model.hints.ArticleHints;
import com.samyosm.loremarticulus.model.hints.TwitterPostHints;
import com.samyosm.loremarticulus.model.hints.YoutubeCommentHints;
import com.samyosm.loremarticulus.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class GeneratorsControllers {
    private final GeneratorController generator;

    @Autowired
    public GeneratorsControllers(UserRepo userRepo, @Value("${tokens.openai}") String apiKey) {
        this.generator = new GeneratorController(userRepo, apiKey);
    }


    @PostMapping("article")
    public String GenerateArticle(@RequestBody ArticleHints hints, @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        return generator.Generate(hints, authToken, ArticleGenerator::MakeQuery);
    }

    @PostMapping("twitter")
    public String GenerateTwitterPost(@RequestBody TwitterPostHints hints, @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        return generator.Generate(hints, authToken, TwitterPostGenerator::MakeQuery);
    }

    @PostMapping("youtube")
    public String GenerateYoutubeComment(@RequestBody YoutubeCommentHints hints, @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        return generator.Generate(hints, authToken, YoutubeCommentGenerator::MakeQuery);
    }
}
