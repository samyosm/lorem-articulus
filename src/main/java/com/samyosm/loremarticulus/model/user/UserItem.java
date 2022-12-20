package com.samyosm.loremarticulus.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
@AllArgsConstructor
@Getter
@Setter
public class UserItem {

    @Id
    private final String token;
    private int usage;
    private final int maxUsage;
}
