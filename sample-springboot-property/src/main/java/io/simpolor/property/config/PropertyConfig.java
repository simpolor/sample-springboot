package io.simpolor.property.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Getter
@Configuration
public class PropertyConfig {

    @Bean
    @ConfigurationProperties(prefix = "application")
    public Application application() {
        return new Application();
    }

    @Data
    public class Application {
        private String name;
        private String email;
    }
}
