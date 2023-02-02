package config;

import config.dto.Api;
import config.dto.Db;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class YamlConfig {
    private Db db;
    private Api api;
}