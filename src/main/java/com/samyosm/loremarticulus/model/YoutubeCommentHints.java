package com.samyosm.loremarticulus.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class YoutubeCommentHints {
    private String videoTitle;
    private String author;
    private String tone;
    private String commentDescription;
}
