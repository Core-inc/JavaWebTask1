package com.teamcore.manageapp.web.security;

import com.teamcore.manageapp.service.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UrlByRoleResolver {

    public static String homeUrl(Collection<? extends GrantedAuthority> authorities) {

        for(GrantedAuthority grantedAuthority: authorities) {
            switch (grantedAuthority.getAuthority()) {
                case Role.Name.ADMIN_ROLE:
                    return "/admin/cabinet";
                case Role.Name.MANAGER_ROLE:
                    return "/manager/cabinet";
                case Role.Name.DEVELOPER_ROLE:
                    return "/developer/cabinet";
                default:
                    throw new IllegalStateException("unknown authority: "+grantedAuthority.getAuthority());
            }
        }

        throw new IllegalStateException("no granted authorities!");
    }

    public static String homeUrl(List<String> roles) {
        for(String role: roles) {
            switch (role) {
                case Role.Name.ADMIN_ROLE:
                    return "/admin/cabinet";
                case Role.Name.MANAGER_ROLE:
                    return "/manager/cabinet";
                case Role.Name.DEVELOPER_ROLE:
                    return "/developer/cabinet";
                default:
                    throw new IllegalStateException("unknown authority: "+role);
            }
        }

        throw new IllegalStateException("no granted authorities!");
    }

}
