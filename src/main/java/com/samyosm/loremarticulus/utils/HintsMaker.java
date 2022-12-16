package com.samyosm.loremarticulus.utils;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class HintsMaker {
    private String hints = "";

    public HintsMaker prompt(String prompt) {
        this.hints += prompt + "\n";
        return this;
    }

    public HintsMaker add(String label, String value) {
        if (value == null) return this;
        hints += label + ": " + value + "\n";
        return this;
    }

    public HintsMaker addList(String label, List<String> list) {
        if (list.isEmpty()) return this;
        final String value = String.join(", ", list);
        hints += label + ": " + value + "\n";
        return this;
    }

    public String build() {
        return hints;
    }
}
