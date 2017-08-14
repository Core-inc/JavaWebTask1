package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CabinetController {

    @RequestMapping(value = "/admin/cabinet", method = RequestMethod.GET)
    public String adminCabinet() {
        return "admin/project_review";
    }

    @RequestMapping(value = "/manager/cabinet", method = RequestMethod.GET)
    public String managerCabinet() {
        return "manager/project_review";
    }

    @RequestMapping(value = "/developer/cabinet", method = RequestMethod.GET)
    public String developerCabinet() {
        return "developer/personal";
    }
}
