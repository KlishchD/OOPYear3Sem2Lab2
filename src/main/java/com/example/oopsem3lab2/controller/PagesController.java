package com.example.oopsem3lab2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@ControllerAdvice
public class PagesController {
    private final ApplicationContext context;

    @Autowired
    public PagesController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "index";
    }

    @GetMapping("/trip-management")
    public String trip_management(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        return "trip-management";
    }

    @GetMapping("/logout")
    public void logout(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("request", request);
        context.getBean(com.example.oopsem3lab2.beans.Controller.class).logout(request);
        response.sendRedirect("/index");
    }
}