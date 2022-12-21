package com.samyosm.loremarticulus.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlReader {
    public static String ReadUrl(URL url) throws IOException {
        try (var inputStream = url.openStream()) {
            return new String(inputStream.readAllBytes());
        }
    }

    public static String ReadUrl(String url) throws IOException {
        return ReadUrl(new URL(url));
    }
}
