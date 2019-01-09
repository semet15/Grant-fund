package com.grant_fund.controller;

import com.grant_fund.model.Admin;
import com.grant_fund.model.GrantFund;
import com.grant_fund.service.AdminService;
import com.grant_fund.service.GrantFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/grantfund")
public class GrantFundController {

    @Autowired
    private GrantFundService grantFundService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ModelAndView getGrantFundsList(ModelAndView modelAndView) {
        List<GrantFund> grantFunds = grantFundService.findAll();
        if (!grantFunds.isEmpty()) {
            Collections.sort(grantFunds, (g1, g2) -> g2.getEndingDate().compareTo(g1.getEndingDate()));
        }

        modelAndView.addObject("grantfunds", grantFunds);
        modelAndView.setViewName("start-grantfund-list");
        return modelAndView;
    }

    @RequestMapping(value="/advanced-list", method = RequestMethod.GET)
    public ModelAndView getGrantFundsAdvancedList(Authentication authentication, ModelAndView modelAndView) {
        Admin admin = adminService.find(authentication.getName());
        modelAndView.addObject("admin", admin);

        List<GrantFund> grantFunds = grantFundService.findAll();
        if (!grantFunds.isEmpty()) {
            Collections.sort(grantFunds, (g1, g2) -> g2.getEndingDate().compareTo(g1.getEndingDate()));
        }

        modelAndView.addObject("grantfunds", grantFunds);
        modelAndView.setViewName("grantfunds-advanced-list");
        return modelAndView;
    }



    @RequestMapping(value="/creating-form", method = RequestMethod.GET)
    public ModelAndView getGrantFundCreatingForm(Authentication authentication, ModelAndView modelAndView) {
        Admin admin = adminService.find(authentication.getName());
        modelAndView.addObject("admin", admin);

        if (grantFundService.findAll().stream().anyMatch(g -> g.getState() == GrantFund.State.ACTIVE)) {
            modelAndView.setViewName("redirect:/grantfund/advanced-list?error=true");
            return modelAndView;
        }

        modelAndView.addObject("grantfund", new GrantFund());
        modelAndView.setViewName("grantfund-creating-form");
        return modelAndView;
    }

    @RequestMapping(value="/creating", method = RequestMethod.POST)
    public ModelAndView createGrantFund(@Valid @ModelAttribute("grantfund") GrantFund grantFund,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {

        if (grantFund.getEndingDate() != null && grantFund.getEndingDate().before(new Date())) {
            FieldError fieldError = new FieldError("grantfund", "endingDate",
            null,
                false,
                                new String[]{"Future.grantfund.endingDate"},
                        null,
                        "Must be in future");
            bindingResult.addError(fieldError);
        }

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("grantfund-creating-form");
            return modelAndView;
        }

        grantFund.setState(GrantFund.State.ACTIVE);
        grantFundService.save(grantFund);
        modelAndView.setViewName("redirect:/grantfund/advanced-list");
        return modelAndView;
    }

    @RequestMapping(value="/distribution-form", method = RequestMethod.GET)
    public ModelAndView getGrantFundDistributionForm(Authentication authentication, ModelAndView modelAndView) {
        Admin admin = adminService.find(authentication.getName());
        modelAndView.addObject("admin", admin);

        GrantFund grantFund = grantFundService.findAll()
                                            .stream()
                                            .filter(g -> g.getState() == GrantFund.State.ACTIVE).findFirst()
                                            .orElse(null);

        if (grantFund != null && grantFund.getEndingDate().after(new Date())) {
            modelAndView.addObject("warning", "ending date is yet to come");
        } else if (grantFund == null) {
            modelAndView.setViewName("redirect:/grantfund/advanced-list");
            return modelAndView;
        }

        modelAndView.addObject("grantfund", grantFund);
        modelAndView.setViewName("grantfund-distribution-form");
        return modelAndView;
    }

    @RequestMapping(value="/distribution", method = RequestMethod.POST)
    public ModelAndView distributeGrantFund(@Valid @ModelAttribute("grantfund") GrantFund grantFund,
                                            BindingResult bindingResult,
                                            ModelAndView modelAndView) {
        if(bindingResult.hasErrors()) {
            for (FieldError fieldError: bindingResult.getFieldErrors()) {
                if (!fieldError.getField().equalsIgnoreCase("endingDate")) { //здесь endingDate может быть null
                    modelAndView.setViewName("grantfund-distribution-form");
                    return modelAndView;
                }
            }
        }

        GrantFund grantFundFromDB = grantFundService.findAll().stream().filter(g -> g.getState() == GrantFund.State.ACTIVE).findFirst().get();
        if (grantFundFromDB == null) {
            modelAndView.setViewName("redirect:/grantfund/advanced-list");
            return modelAndView;
        }
        grantFundFromDB.setGrantFundSum(grantFund.getGrantFundSum());
        grantFundFromDB.setMinSum(grantFund.getMinSum());

        grantFundService.generateResults(grantFundFromDB);

        if (grantFundFromDB.getEndingDate().after(new Date())) {
            grantFundFromDB.setEndingDate(new Date());
        }

        grantFundService.save(grantFundFromDB);
        modelAndView.setViewName("redirect:/grantfund/advanced-list");
        return modelAndView;
    }
}
