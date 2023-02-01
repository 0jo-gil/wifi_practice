package config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import config.dto.Db;

import java.io.*;
import java.util.Optional;

public class YamlParser {
    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final InputStream configInputStream = YamlParser.class.getClassLoader().getResourceAsStream("config.yaml");

    public static <T> Optional<T> getParsedCrawlerConfig(Class<T> crawlerClz) {
        try {
            return Optional.ofNullable(objectMapper.readValue(configInputStream, crawlerClz));
        } catch (IOException e) {
        }
        return Optional.empty();
    }
}