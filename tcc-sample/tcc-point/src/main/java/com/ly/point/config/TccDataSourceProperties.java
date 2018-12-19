package com.ly.point.config;


import lombok.Data;

@Data
public class TccDataSourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
