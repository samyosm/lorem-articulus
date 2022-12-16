package com.samyosm.loremarticulus.model.gptcompletion;

import com.samyosm.loremarticulus.generator.GeneratorConfig;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GPTCompletionRequest {
    final String model = GeneratorConfig.MODEL;
    private String prompt;
    final int max_tokens = GeneratorConfig.MAX_TOKEN;
    final double temperature = GeneratorConfig.TEMPERATURE;
}
