package com.phamthainguyen.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phamthainguyen.website.model.entity.User;
import com.phamthainguyen.website.model.request.BuyRequest;
import com.phamthainguyen.website.model.response.StatusResponse;
import com.phamthainguyen.website.service.PayService;

@RestController
@RequestMapping("/api/client/buy")
public class BuyController {

    @Autowired
    private PayService payService;

    @PostMapping
    public StatusResponse postBuy(@RequestBody BuyRequest request) {
        System.out.println("request: "+request.toString());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            System.out.println("request: "+request.toString());
            return payService.buy(user.getId(), request);
        }
        return StatusResponse.builder().status("falll").build();
    }

}
