package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TagCounter {
    public static Map<String, Integer> countTags(String urlStr) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        URL url = new URL(urlStr);

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {

            String line;
            Pattern p = Pattern.compile("<\\s*([a-zA-Z0-9]+)");
            while ((line = br.readLine()) != null) {
                Matcher m = p.matcher(line);
                while (m.find()) {
                    String tag = m.group(1).toLowerCase();
                    map.put(tag, map.getOrDefault(tag, 0) + 1);
                }
            }
        }
        return map;
    }
}