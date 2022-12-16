package com.samyosm.loremarticulus.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ArticleHints {

    private String title;
    private String description;
    private List<String> tags;

}
