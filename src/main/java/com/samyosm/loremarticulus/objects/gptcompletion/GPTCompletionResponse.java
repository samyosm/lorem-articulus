package com.samyosm.loremarticulus.objects.gptcompletion;

import lombok.ToString;

import java.util.ArrayList;

@ToString
public class GPTCompletionResponse {
    public ArrayList<Choice> choices;
}
