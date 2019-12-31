package io.simpolor.property.component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationProperty {

    @Value("${application.name}")
    private String name;

    @Value("${application.email}")
    private String email;

}
