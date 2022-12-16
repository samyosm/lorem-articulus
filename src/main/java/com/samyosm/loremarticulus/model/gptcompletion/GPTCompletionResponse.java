package com.samyosm.loremarticulus.model.gptcompletion;

import lombok.*;

import java.util.ArrayList;
@ToString
public class GPTCompletionResponse {
    public ArrayList<Choice> choices;
}
