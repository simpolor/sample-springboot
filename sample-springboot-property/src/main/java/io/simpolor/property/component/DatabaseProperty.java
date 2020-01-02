package io.simpolor.property.component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource("classpath:database.yml")
@ConfigurationProperties(prefix = "database")
public class DatabaseProperty {

    @Value("${host}")
    private String host;

    @Value("${port}")
    private String port;

}
