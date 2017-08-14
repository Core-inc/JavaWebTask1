package com.teamcore.manageapp.web.controllers;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class ErrorHandler implements EmbeddedServletContainerCustomizer {


    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(
                new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
                new ErrorPage(HttpStatus.FORBIDDEN, "/403"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
    }

    @RequestMapping(path = "/404", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "general/errors/404";
    }

    @RequestMapping(path = "/403", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDenied() {
        return "general/errors/403";
    }

    @RequestMapping(path = "/500", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String serverError() {
        return "general/errors/error";
    }

}
