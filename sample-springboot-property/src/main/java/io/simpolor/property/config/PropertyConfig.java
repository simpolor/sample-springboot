package io.simpolor.property.config;

import lombok.Getter;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// @Getter
// @Component
// @PropertySource("classpath:application.yml")
// @ConfigurationProperties("app")
public class PropertyConfig {

    private String name;

    private String email;
}
