package com.phamthainguyen.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.phamthainguyen.website.model.request.AddItemRequest;
import com.phamthainguyen.website.model.request.EditItemRequest;
import com.phamthainguyen.website.model.response.HomeResponse;
import com.phamthainguyen.website.model.response.StatusResponse;
import com.phamthainguyen.website.model.response.TypeItemResponse;
import com.phamthainguyen.website.service.FileService;
import com.phamthainguyen.website.service.ItemService;

@RestController
@RequestMapping("/api/admin/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private FileService fileService;



    @PostMapping
    public StatusResponse addItem(@RequestBody AddItemRequest request) {
        return itemService.addItem(request);
    }

    @PutMapping("/{id}")
    public StatusResponse editItem(@PathVariable Long id, @RequestBody EditItemRequest request){
        return itemService.editItem(id, request);
    }

    @DeleteMapping("/{id}")
    public StatusResponse deleteItem(@PathVariable Long id){
        return itemService.deleteItem(id);
    }

    @GetMapping("/types")
    public TypeItemResponse getType() {
        return itemService.getTypeItem();
    }

    @PostMapping("/upload")
    public StatusResponse upload(@RequestParam("file") MultipartFile file){
        return fileService.saveImage(file);
    }

    @GetMapping("/home")
    public HomeResponse getTop10(){
        return itemService.getHomePage();
    }
}