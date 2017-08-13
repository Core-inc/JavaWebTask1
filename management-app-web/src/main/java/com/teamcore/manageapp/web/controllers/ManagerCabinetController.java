package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagerCabinetController {
    @RequestMapping(value = "/cabinet/manager", method = RequestMethod.GET)
    public String managerCabinet() {
        return "/manager/project_review";
    }

    @RequestMapping(value = "/cabinet/manager/devs", method = RequestMethod.GET)
    public String managerCabinetDevs() {
        return "/manager/dev_base";
    }

    @RequestMapping(value = "/cabinet/manager/add_project", method = RequestMethod.GET)
    public String managerCabinetAddProject() {
        return "/manager/add_project";
    }
}
