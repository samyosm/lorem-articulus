package com.samyosm.loremarticulus.controller;
import com.samyosm.loremarticulus.generator.ArticleGenerator;
import com.samyosm.loremarticulus.model.ArticleHints;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    @Value("${tokens.openai}")
    private String apiKey;

    @PostMapping
    public String GenerateArticle(@RequestBody ArticleHints article) {
        return new ArticleGenerator().Generate(article, apiKey);
    }
}
