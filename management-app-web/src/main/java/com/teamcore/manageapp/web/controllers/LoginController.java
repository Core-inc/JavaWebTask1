package com.teamcore.manageapp.web.controllers;

import com.teamcore.manageapp.web.controllers.utils.UrlBuilder;
import com.teamcore.manageapp.web.security.RoleUtil;
import com.teamcore.manageapp.web.security.UrlByRoleResolver;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {

        User securityUser = RoleUtil.getSecurityUser();
        if (securityUser != null) {
            return UrlBuilder.redirectUrl(
                    UrlByRoleResolver.homeUrl(
                            securityUser.getAuthorities()
                    )
            );
        }


//        HttpSession session = request.getSession();
//        if (session.getAttribute("authFail") != null) {
//            model.addAttribute("authFail", true);
//            session.removeAttribute("authFail");
//        }

        return "general/authorization";
    }

}
