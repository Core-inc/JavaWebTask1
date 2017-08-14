package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manager")
public class ManagerCabinetController {

    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String managerCabinet() {
        return "/manager/project_review";
    }

    @RequestMapping(value = "/cabinet/developers", method = RequestMethod.GET)
    public String managerCabinetDevelopers() {
        return "/manager/dev_base";
    }

    @RequestMapping(value = "/cabinet/add_project", method = RequestMethod.GET)
    public String managerCabinetAddProject() {
        return "/manager/add_project";
    }

    @RequestMapping(value = "/cabinet/add_dev", method = RequestMethod.GET)
    public String managerCabinetAddDeveloper() {
        return "/manager/add_dev";
    }

    @RequestMapping(value = "/cabinet/dev_info", method = RequestMethod.GET)
    public String managerCabinetDeveloperInfo() {
        return "/manager/dev_info";
    }
}
