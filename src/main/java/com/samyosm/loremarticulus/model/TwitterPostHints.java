package com.samyosm.loremarticulus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TwitterPostHints {
    private List<String> topics;
    private String tone;
    private String description;
}
