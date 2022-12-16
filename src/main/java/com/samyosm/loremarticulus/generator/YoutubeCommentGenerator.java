package com.samyosm.loremarticulus.generator;

import com.samyosm.loremarticulus.generator.utility.Generator;
import com.samyosm.loremarticulus.model.YoutubeCommentHints;
import com.samyosm.loremarticulus.utils.HintsMaker;

public class YoutubeCommentGenerator implements Generator<YoutubeCommentHints> {
    @Override
    public String MakeQuery(YoutubeCommentHints hints) {
        final String prompt = "From the hints below, write a comment for a YouTube video.\n Hints:\n";
        return new HintsMaker()
                .prompt(prompt)
                .add("Video Title", hints.getVideoTitle())
                .add("Author", hints.getAuthor())
                .add("Tone", hints.getTone())
                .add("Comment Description", hints.getCommentDescription())
                .build();
    }
}
