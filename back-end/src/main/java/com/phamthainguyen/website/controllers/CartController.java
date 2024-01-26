package com.phamthainguyen.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.phamthainguyen.website.service.CartService;

@RestController
@RequestMapping("/api/client/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public StatusResponse addCart(@RequestBody ItemRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            if (request.getCount() > 0) {
                cartService.addItem(user.getEmail(), request.getId(), request.getCount());
                return StatusResponse.builder().status("success").build();
            }
            
        }
        return StatusResponse.builder().status("fall").build();
    }

    @GetMapping
    public CartResponse getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return cartService.getAllItemCart(user.getEmail());
        } else {
            return CartResponse.builder().build();
        }
    }

    @DeleteMapping("/{id}")
    public StatusResponse postDeleteItemCart(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return cartService.deleteItemCart(user.getEmail(), id);
        } else {
            return StatusResponse.builder().status("falll").build();
        }
    }
}
