package com.teamcore.manageapp.web.controllers.utils;

public class UrlBuilder {

    public static String forwardUrl(String url) {
        return "forward:"+url;
    }

    public static String redirectUrl(String url) {
        return "redirect:"+url;
    }
}
