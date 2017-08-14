package com.teamcore.manageapp.web.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;

public class RoleUtil {

    public static User getSecurityUser() {
        if(SecurityContextHolder.getContext() == null) { return null; }
        if(SecurityContextHolder.getContext().getAuthentication() == null) { return  null; }

        Object principalObj = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return principalObj instanceof User ? (User)principalObj: null;

    }
}
