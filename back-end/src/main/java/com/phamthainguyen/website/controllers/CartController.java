package com.phamthainguyen.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phamthainguyen.website.model.entity.User;
import com.phamthainguyen.website.model.request.ItemRequest;
import com.phamthainguyen.website.model.response.CartResponse;
import com.phamthainguyen.website.model.response.StatusResponse;
import com.phamthainguyen.website.service.AuthService;
import com.phamthainguyen.website.service.CartService;

@RestController
@RequestMapping("/api/client/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public StatusResponse addCart(@RequestBody ItemRequest request) {
        User user = authService.getUser();
        if (user == null || request.getCount() > 0) {
            return StatusResponse.builder().status("fall").build();
        }
        cartService.addItem(user, request.getId(), request.getCount());
        return StatusResponse.builder().status("success").build();

    }

    @GetMapping
    public CartResponse getCart() {
        User user = authService.getUser();
        if (user == null) {
            return CartResponse.builder().build();
        }
        return cartService.getAllItemCart(user);
    }

    @DeleteMapping("/{id}")
    public StatusResponse postDeleteItemCart(@PathVariable Long id) {
        User user = authService.getUser();
        if (user == null) {
            return StatusResponse.builder().status("falll").build();
        }
        return cartService.deleteItemCart(user, id);
    }
}
