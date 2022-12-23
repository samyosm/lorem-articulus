package com.samyosm.loremarticulus.objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistration {
    private final String uid;
    private final int maxUsage;
}
