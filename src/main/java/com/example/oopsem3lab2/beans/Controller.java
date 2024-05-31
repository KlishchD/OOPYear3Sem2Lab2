package com.example.oopsem3lab2.beans;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Controller {
    public boolean isLoggedIn(HttpServletRequest req) {
        return getSession(req) != null;
    }

    public boolean isLogoutAction(HttpServletRequest req) {
        return getAction(req).equals("logout");
    }

    public boolean hasRole(HttpServletRequest req, String role) {
        return req.isUserInRole(role);
    }

    public String getUsername(HttpServletRequest req) {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private String getReferrerUri(HttpServletRequest req) {
        StringBuffer uri = req.getRequestURL();
        String q = req.getQueryString();
        if (q != null) {
            uri.append("?").append(q);
        }
        return uri.toString();
    }

    private KeycloakSecurityContext getSession(HttpServletRequest req) {
        if (req == null) {
            return null;
        }
        return (KeycloakSecurityContext) req.getAttribute(KeycloakSecurityContext.class.getName());
    }

    public void logout(HttpServletRequest req) {
        try {
            req.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAction(HttpServletRequest req) {
        if (req.getParameter("action") == null) return "";
        return req.getParameter("action");
    }
}