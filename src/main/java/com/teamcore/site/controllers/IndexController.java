package com.teamcore.site.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by igoz on 26.07.17.
 */
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
