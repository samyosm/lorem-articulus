package com.samyosm.loremarticulus.utils;

import kong.unirest.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static com.samyosm.loremarticulus.utils.UrlReader.ReadUrl;

public class GeneratorQueryLinks {

    public static String Get(String link, String type) throws IOException {
        var rawLinks = ReadUrl(link);
        var links = new JSONObject(rawLinks);

        if (!links.has(type))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "/" + type + "doesn't exists.");

        return links.get(type).toString();
    }

}
