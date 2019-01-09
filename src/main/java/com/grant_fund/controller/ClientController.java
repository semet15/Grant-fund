package com.grant_fund.controller;

import com.grant_fund.model.Client;
import com.grant_fund.model.ClientDetails;
import com.grant_fund.model.GrantFund;
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
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private GrantFundService grantFundService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView getClientPage(Authentication authentication, ModelAndView modelAndView) {
        List<GrantFund> grantFunds = grantFundService.findAll();
        if (!grantFunds.isEmpty()) {
            Collections.sort(grantFunds, (g1, g2) -> g2.getEndingDate().compareTo(g1.getEndingDate()));
        }

        modelAndView.addObject("grantfunds", grantFunds);

        Client client = clientService.find(authentication.getName());
        modelAndView.addObject("client", client);

        modelAndView.setViewName("client-page");
        return modelAndView;
    }

    @RequestMapping(value="/signup-form", method = RequestMethod.GET)
    public ModelAndView getClientForm(ModelAndView modelAndView) {
        Client client = new Client();
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClient(client);
        client.setClientDetails(clientDetails);

        modelAndView.addObject("client", client);
        modelAndView.setViewName("client-signup-form");
        return modelAndView;
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ModelAndView signupClient(@Valid @ModelAttribute Client client,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView) {
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("client-signup-form");
            return modelAndView;
        }

        if (userService.find(client.getUsername()) != null) {
            modelAndView.setViewName("redirect:/client/signup-form?error=true");
            return modelAndView;
        }

        client.getClientDetails().setClient(client);
        clientService.save(client);
        modelAndView.setViewName("redirect:/start-page");
        return modelAndView;
    }

}
