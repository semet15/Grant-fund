package com.grant_fund.controller;

import com.grant_fund.model.Admin;
import com.grant_fund.model.GrantFund;
import com.grant_fund.service.AdminService;
import com.grant_fund.service.GrantFundService;
import com.grant_fund.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private GrantFundService grantFundService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView getAdminPage(Authentication authentication, ModelAndView modelAndView) {
        List<GrantFund> grantFunds = grantFundService.findAll();
        if (!grantFunds.isEmpty()) {
            Collections.sort(grantFunds, (g1, g2) -> g2.getEndingDate().compareTo(g1.getEndingDate()));
        }

        modelAndView.addObject("grantfunds", grantFunds);

        Admin admin = adminService.find(authentication.getName());
        modelAndView.addObject("admin", admin);

        modelAndView.setViewName("admin-page");
        return modelAndView;
    }

    @RequestMapping(value="/signup-form", method = RequestMethod.GET)
    public ModelAndView getClientForm(ModelAndView modelAndView) {

        modelAndView.addObject("admin", new Admin());
        modelAndView.setViewName("admin-signup-form");
        return modelAndView;
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ModelAndView signupClient(@Valid @ModelAttribute Admin admin,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView) {
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("admin-signup-form");
            return modelAndView;
        }

        if (userService.find(admin.getUsername()) != null) {
            modelAndView.setViewName("redirect:/admin/signup-form?error=true");
            return modelAndView;
        }

        adminService.save(admin);
        modelAndView.setViewName("redirect:/admin/");
        return modelAndView;
    }
}
