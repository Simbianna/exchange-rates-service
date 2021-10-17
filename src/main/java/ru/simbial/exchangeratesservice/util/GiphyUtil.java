package ru.simbial.exchangeratesservice.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GiphyUtil {

    public static String getGifUrlFromGiphyJson(String json) {
        JsonObject o = JsonParser.parseString(json).getAsJsonObject();
        return o.get("data").getAsJsonObject().get("image_url").getAsString();
    }

    public static String getGiphyWebpFormatFromGifUrl(String gifUrl) {
        return gifUrl.replaceAll("media[0-9]*\\.", "i\\.");
    }

    public static byte[] getUrlImageBytes(String imageUrl) {
        byte[] result = null;
        try (InputStream in = new URL(imageUrl).openStream()) {
            result = IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
