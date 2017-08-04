package com.teamcore.manageapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("authFail") != null) {
            model.addAttribute("authFail", true);
            session.removeAttribute("authFail");
        }
        return "login";
    }
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, HttpServletResponse response) {
//        if (response.getHeader("authFail") != null) {
//            model.addAttribute("authFail", true);
//        }
//        return "login";
//    }

}
