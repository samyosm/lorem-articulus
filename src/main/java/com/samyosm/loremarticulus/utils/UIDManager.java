package com.samyosm.loremarticulus.utils;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.samyosm.loremarticulus.config.SecurityConfig;

public class UIDManager {
    public static String getRandomToken() {
        return NanoIdUtils.randomNanoId();
    }

    public static String refactorUid(String uid) {
        if (uid.length() < SecurityConfig.UID_LENGTH)
            throw new IllegalArgumentException("Uid must be at least " + SecurityConfig.UID_LENGTH + " characters long");
        return uid.substring(0, SecurityConfig.UID_LENGTH);
    }

    public static String getRandomUidToken(String uid) {
        var refactoredUid = refactorUid(uid);
        return refactoredUid + "-" + getRandomToken();
    }
}
