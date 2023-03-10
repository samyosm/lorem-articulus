package com.samyosm.loremarticulus.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("user")
@AllArgsConstructor
@Getter
@Setter
public class UserItem {

    @Id
    private final String id;
    private final List<String> tokens;
    private final int maxUsage;
    private int usage;
}
