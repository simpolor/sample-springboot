package io.simpolor.property.controller;

import io.simpolor.property.component.ApplicationProperty;
import io.simpolor.property.component.DatabaseProperty;
import io.simpolor.property.config.PropertyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class WelcomeController {

    @Autowired
    private ApplicationProperty applicationProperty;

    @Autowired
    private DatabaseProperty databaseProperty;

    @Autowired
    private PropertyConfig propertyConfig;

    @ResponseBody
    @RequestMapping({"/", "/index", "/welcome"})
    public String welcome() {
        return "Springboot Sample Mybatis";
    }

    @ResponseBody
    @RequestMapping({"/test"})
    public String test() {

        log.info("applicationProperty.name :{}", applicationProperty.getName());
        log.info("applicationProperty.email : {}", applicationProperty.getEmail());

        log.info("----");

        log.info("propertyConfig.name : {}", propertyConfig.application().getName());
        log.info("propertyConfig.email : {}", propertyConfig.application().getEmail());

        log.info("----");

        log.info("databaseProperty.host : {}", databaseProperty.getHost());
        log.info("databaseProperty.port : {}", databaseProperty.getPort());

        return "Success";
    }
}
