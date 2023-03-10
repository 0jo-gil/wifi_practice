package config.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Db {
    private String name;
    private String user;
    private String password;
    private String url;
}