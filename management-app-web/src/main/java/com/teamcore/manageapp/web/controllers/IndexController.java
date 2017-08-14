package com.teamcore.manageapp.web.controllers;

import com.teamcore.manageapp.web.controllers.utils.UrlBuilder;
import com.teamcore.manageapp.web.security.RoleUtil;
import com.teamcore.manageapp.web.security.UrlByRoleResolver;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String index() {

        User securityUser = RoleUtil.getSecurityUser();
        if (securityUser != null) {
            return UrlBuilder.redirectUrl(
                    UrlByRoleResolver.homeUrl(
                            securityUser.getAuthorities()
                    )
            );
        }

        return "general/authorization";
    }
}
