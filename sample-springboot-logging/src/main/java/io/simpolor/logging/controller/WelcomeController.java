package io.simpolor.logging.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping(value = {"/", "/index", "/main", "/welcome"})
    public String welcome(){
        return "Welcome to logging";
    }

}
