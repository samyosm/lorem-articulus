package com.samyosm.loremarticulus.generator;

import com.samyosm.loremarticulus.model.hints.TwitterPostHints;
import com.samyosm.loremarticulus.utils.HintsMaker;

public class TwitterPostGenerator {
    public static String MakeQuery(TwitterPostHints hints) {
        final String prompt = "From the hints below, write a Twitter post.\n Hints:\n";
        return new HintsMaker()
                .prompt(prompt)
                .addList("Topics", hints.getTopics())
                .add("Tone", hints.getTone())
                .add("Description", hints.getDescription())
                .build();
    }
}
