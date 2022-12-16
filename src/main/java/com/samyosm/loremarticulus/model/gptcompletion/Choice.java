package com.samyosm.loremarticulus.model.gptcompletion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public
class Choice {
    private String text;
    private int index;
    private String finish_reason;
}

