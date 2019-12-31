package io.simpolor.property.controller;

import io.simpolor.property.component.ApplicationProperty;
import io.simpolor.property.component.DatabaseProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

    @Autowired
    private ApplicationProperty applicationProperty;

    @Autowired
    private DatabaseProperty databaseProperty;

    @ResponseBody
    @RequestMapping({"/", "/index", "/welcome"})
    public String welcome() {
        return "Springboot Sample Mybatis";
    }

    @ResponseBody
    @RequestMapping({"/test"})
    public String test() {

        System.out.println("applicationProperty.name : "+applicationProperty.getName());
        System.out.println("applicationProperty.email : "+applicationProperty.getEmail());

        System.out.println("----");

        System.out.println("appProperty.name : "+databaseProperty.getHost());
        System.out.println("appProperty.email : "+databaseProperty.getPort());

        return "Success";
    }
}
