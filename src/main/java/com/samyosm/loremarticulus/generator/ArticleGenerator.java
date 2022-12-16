package com.samyosm.loremarticulus.generator;

import com.samyosm.loremarticulus.generator.Generator;
import com.samyosm.loremarticulus.model.ArticleHints;

public class ArticleGenerator implements Generator<ArticleHints> {
    @Override
    public String MakeQuery(ArticleHints article) {
        final String mainPrompt = "From the hints below, generate a 1500 to 3000 words article.\n\n Hints:\n";
        final String tags = String.join(", ", article.getTags());
        return mainPrompt +
                "Title: " + article.getTitle() + "\n" +
                "Description: " + article.getDescription() + "\n" +
                "Tags: " + tags;
    }
}
