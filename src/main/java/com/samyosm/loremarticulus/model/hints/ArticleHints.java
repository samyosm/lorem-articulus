package com.samyosm.loremarticulus.model.hints;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ArticleHints {

    private String title;
    private String description;
    private List<String> tags;

}
