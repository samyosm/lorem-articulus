package com.samyosm.loremarticulus.model.gptcompletion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GPTCompletionRequest {
    final String model = "text-davinci-003";
    String prompt;
    int max_tokens;
    double temperature;
}
