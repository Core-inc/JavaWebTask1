package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manager")
public class ProjectManagementController {
    @RequestMapping(value = "/cabinet/project/{id}", method = RequestMethod.GET)
    public String projectInfo() {
        return "/manager/project/project_info";
    }

    @RequestMapping(value = "/cabinet/project/{id}/add_task", method = RequestMethod.GET)
    public String addTask() {
        return "/manager/project/add_task";
    }

    @RequestMapping(value = "/cabinet/project/{id}/tech_task", method = RequestMethod.GET)
    public String techTask() {
        return "/manager/project/tech_task";
    }

    @RequestMapping(value = "/cabinet/project/{id}/status", method = RequestMethod.GET)
    public String projectStatus() {
        return "/manager/project/task_info";
    }
}
