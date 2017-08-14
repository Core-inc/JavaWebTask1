package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dev")
public class DeveloperCabinetController {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String developerInfo() {
        return "/developer/personal";
    }

    @RequestMapping(value = "/{id}/portfolio", method = RequestMethod.GET)
    public String developerPortfolio() {
        return "/developer/portfolio";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String developerRegistration() {
        return "/developer/registration";
    }
}
