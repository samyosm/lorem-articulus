package com.samyosm.loremarticulus.controller;
import com.samyosm.loremarticulus.model.ArticleHints;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    @PostMapping
    public String GenerateArticle(@RequestBody ArticleHints article) {
        return new ArticleGenerator().Generate(article);
    }
}
