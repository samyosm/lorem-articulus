package com.samyosm.loremarticulus.utils;

import com.samyosm.loremarticulus.exceptions.BadRequestException;
import kong.unirest.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static com.samyosm.loremarticulus.utils.UrlReader.ReadUrl;

public class GeneratorQuery {

    public static String get(String linksRedirect, String type) throws IOException {
        var rawLinks = ReadUrl(linksRedirect);
        var links = new JSONObject(rawLinks);

        if (!links.has(type))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "/" + type + "doesn't exists.");

        return links.get(type).toString();
    }

    public static String getFromBaseUrl(String baseUrl, String type) {
        return baseUrl + "/" + type + ".js";
    }

    public static String getScript(String baseUrl, String type) {
        final String script;
        try {
            script = ReadUrl(getFromBaseUrl(baseUrl, type));
        } catch (IOException e) {
            throw new BadRequestException("Endpoint /" + type + " doesn't exists.");
        }
        return script;
    }

}
