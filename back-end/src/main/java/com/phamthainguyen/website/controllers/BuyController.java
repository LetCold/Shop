package com.phamthainguyen.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phamthainguyen.website.model.entity.User;
import com.phamthainguyen.website.model.request.BuyRequest;
import com.phamthainguyen.website.model.response.StatusResponse;
import com.phamthainguyen.website.service.AuthService;
import com.phamthainguyen.website.service.PayService;

@RestController
@RequestMapping("/api/client/buy")
public class BuyController {

    @Autowired
    private PayService payService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public StatusResponse postBuy(@RequestBody BuyRequest request) {
        User user =  authService.getUser();
        if (user == null) {
            return StatusResponse.builder().status("falll").build();
        }
        return payService.buy(user.getId(), request);
    }

}
