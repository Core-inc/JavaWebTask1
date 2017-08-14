package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping()
public class GeneralController {
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return "/general/info";
    }

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public String team() {
        return "/general/team";
    }

    @RequestMapping(value = "/tech", method = RequestMethod.GET)
    public String tech() {
        return "/general/tech";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String techtaskUpload() {
        return "/general/techtask_upload";
    }
}
