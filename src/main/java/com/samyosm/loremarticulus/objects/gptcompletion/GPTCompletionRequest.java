package com.samyosm.loremarticulus.objects.gptcompletion;

import com.samyosm.loremarticulus.config.GeneratorConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GPTCompletionRequest {
    final String model = GeneratorConfig.MODEL;
    final int max_tokens = GeneratorConfig.MAX_TOKEN;
    final double temperature = GeneratorConfig.TEMPERATURE;
    private String prompt;
}
