package io.simpolor.property.component;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/***
 * @ConfigurationProperties를 사용하기 위해서는,
 * Mavne에서 spring-boot-configuration-processor 추가가 필요
 */
@Getter
@Component
@PropertySource("classpath:database.yml")
@ConfigurationProperties(prefix = "database")
@Validated
public class DatabaseProperty {

    @Value("${host}")
    private String host;

    @Length(min = 5, max = 10)
    @Value("${port}")
    private String port;

}
