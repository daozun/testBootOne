package com.example.testBootOne.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtConfig {
    public static final String REDIS_TOKEN_KEY_PREFIX = "TOKEN_";
    private long time;     // 5天(以秒s计)过期时间
    private String secret;// JWT密码
    private String prefix;         // Token前缀
    private String header; // 存放Token的Header Key

    public void setTime(long time) {
        this.time = time;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
