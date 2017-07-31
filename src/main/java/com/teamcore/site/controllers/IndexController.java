package com.teamcore.site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by igoz on 26.07.17.
 */

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
