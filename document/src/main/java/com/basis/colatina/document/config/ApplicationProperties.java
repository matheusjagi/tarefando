package com.basis.colatina.document.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "application")
@Configuration
@Getter
@Setter
public class ApplicationProperties {

    private MinioProperties minio = new MinioProperties();

    @Getter
    @Setter
    public static class MinioProperties {
        private String username;
        private String password;
        private String bucker;
        private String url;
    }
}
