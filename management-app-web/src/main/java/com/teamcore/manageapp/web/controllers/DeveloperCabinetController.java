package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/developer")
public class DeveloperCabinetController {
    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String developerInfo() {
        return "/developer/personal";
    }

    @RequestMapping(value = "/cabinet/portfolio", method = RequestMethod.GET)
    public String developerPortfolio() {
        return "/developer/portfolio";
    }

    @RequestMapping(value = "/new/registration", method = RequestMethod.GET)
    public String developerRegistration() {
        return "/developer/registration";
    }
}
