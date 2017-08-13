package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProjectManagementController {
    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public String projectInfo() {
        return "/manager/project/project_info";
    }
}
