package com.phamthainguyen.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phamthainguyen.website.model.entity.Item;
import com.phamthainguyen.website.model.request.SearchRequest;
import com.phamthainguyen.website.model.response.ShopResponse;
import com.phamthainguyen.website.service.ItemService;

@RestController
@RequestMapping("/api/client/shop")
public class ShopController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ShopResponse getShop() {
        return itemService.getAllItem();
    }

    @GetMapping("/category/{id}")
    public ShopResponse getCategory(@PathVariable Long id) {
        return itemService.getAllItemByCategory(id);
    }

    @PostMapping("/search")
    public ShopResponse getSearch(@RequestBody SearchRequest request) {
        return itemService.getAllItemBySearch(request);
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItem(id);
    }
}
