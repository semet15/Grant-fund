package com.grant_fund.controller;

import com.grant_fund.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value="/redirect-by-role", method = RequestMethod.GET)
    public ModelAndView redirectByRole(Authentication authentication, ModelAndView modelAndView) {

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equalsIgnoreCase("ROLE_CLIENT"))) {
            modelAndView.setViewName("redirect:/client/");
        } else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equalsIgnoreCase("ROLE_ADMIN"))) {
            modelAndView.setViewName("redirect:/admin/");
        } else {
            modelAndView.setViewName("redirect:/start-page");
        }

        return modelAndView;
    }

}
