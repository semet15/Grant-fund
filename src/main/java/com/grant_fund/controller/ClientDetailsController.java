package com.grant_fund.controller;

import com.grant_fund.model.Client;
import com.grant_fund.model.ClientDetails;
import com.grant_fund.model.GrantFund;
import com.grant_fund.service.ClientDetailsService;
import com.grant_fund.service.ClientService;
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
@RequestMapping("/client-details")
public class ClientDetailsController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ModelAndView getClientDetails(Authentication authentication, ModelAndView modelAndView) {

        Client client = clientService.find(authentication.getName());
        modelAndView.addObject("client", client);
        modelAndView.addObject("clientDetails", client.getClientDetails());

        modelAndView.setViewName("client-details-list");
        return modelAndView;
    }

    @RequestMapping(value="/edition-form", method = RequestMethod.GET)
    public ModelAndView getClientDetailsEditionForm(Authentication authentication, ModelAndView modelAndView) {
        Client client = clientService.find(authentication.getName());
        modelAndView.addObject("client", client);
        modelAndView.addObject("clientDetails", client.getClientDetails());

        modelAndView.setViewName("client-details-edition-form");
        return modelAndView;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public ModelAndView editClientDetails(@Valid @ModelAttribute ClientDetails clientDetails,
                                          BindingResult bindingResult,
                                          ModelAndView modelAndView,
                                          Authentication authentication) {
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("client-details-edition-form");
            return modelAndView;
        }

        Client clientDB = clientService.find(authentication.getName());
        clientDetails.setClientId(clientDB.getUserId());
        clientDB.setClientDetails(clientDetails);

        clientService.save(clientDB);

        modelAndView.setViewName("redirect:/client-details/list");
        return modelAndView;
    }
}
