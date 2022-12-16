package com.samyosm.loremarticulus.generator;

import com.samyosm.loremarticulus.model.ArticleHints;
import com.samyosm.loremarticulus.utils.HintsMaker;

public class ArticleGenerator implements Generator<ArticleHints> {
    @Override
    public String MakeQuery(ArticleHints article) {
        final String prompt = "From the hints below, generate a 1500 to 3000 words article.\n\n Hints:\n";
        return new HintsMaker()
                .prompt(prompt)
                .add("Title", article.getTitle())
                .add("Description", article.getDescription())
                .addList("Tags", article.getTags())
                .build();
    }
}
