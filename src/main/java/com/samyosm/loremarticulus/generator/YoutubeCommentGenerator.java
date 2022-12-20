package com.samyosm.loremarticulus.generator;

import com.samyosm.loremarticulus.model.hints.YoutubeCommentHints;
import com.samyosm.loremarticulus.utils.HintsMaker;

public class YoutubeCommentGenerator {

    public static String MakeQuery(YoutubeCommentHints hints) {
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
