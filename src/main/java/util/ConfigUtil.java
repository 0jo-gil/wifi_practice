package util;

import config.YamlConfig;
import config.dto.*;
import config.YamlParser;

public class ConfigUtil {
    public static final YamlConfig yamlConfig = YamlParser.getParsedCrawlerConfig(YamlConfig.class).get();

    public static Db getDbConfig(){
        return yamlConfig.getDb();
    }

    public static Api getApiConfig(){
        return yamlConfig.getApi();
    }

    public static void main(String[] args) {
    }
}