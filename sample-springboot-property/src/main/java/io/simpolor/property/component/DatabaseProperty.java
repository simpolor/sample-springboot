package io.simpolor.property.component;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:database.yml")
@ConfigurationProperties(prefix = "database")
public class DatabaseProperty {

    private String host;

    private String port;

}
