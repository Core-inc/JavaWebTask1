package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer_cabinet")
public class CustomerCabinetControlelr {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String customerInfo() {
        return "/customer/personal";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String customerRegistration() {
        return "/customer/registration";
    }
}
