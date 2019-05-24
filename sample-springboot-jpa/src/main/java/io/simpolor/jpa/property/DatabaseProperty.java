package io.simpolor.jpa.property;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:database.properties")
public class DatabaseProperty {

    @Value("${database.mysql.driver}")
    private String driver;

    @Value("${database.mysql.url}")
    private String url;

    @Value("${database.mysql.username}")
    private String username;

    @Value("${database.mysql.password}")
    private String password;
}
