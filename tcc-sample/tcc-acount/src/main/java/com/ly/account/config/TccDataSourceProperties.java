package com.ly.account.config;


import lombok.Data;

@Data
public class TccDataSourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
