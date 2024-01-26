package com.phamthainguyen.website.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamthainguyen.website.model.entity.Item;
import com.phamthainguyen.website.model.entity.TypeItem;
import com.phamthainguyen.website.model.request.AddItemRequest;
import com.phamthainguyen.website.model.request.EditItemRequest;
import com.phamthainguyen.website.model.request.SearchRequest;
import com.phamthainguyen.website.model.response.HomeResponse;
import com.phamthainguyen.website.model.response.ShopResponse;
import com.phamthainguyen.website.model.response.StatusResponse;
import com.phamthainguyen.website.model.response.TypeItemResponse;
import com.phamthainguyen.website.responsitory.ItemRepository;
import com.phamthainguyen.website.responsitory.TypeItemRepository;
import com.phamthainguyen.website.util.JsonConven;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemResponsitory;

    @Autowired
    private TypeItemRepository typeItemRepository;

    @Autowired
    private FileService fileService;

    public ShopResponse getAllItem() {
        List<Item> listItem = itemResponsitory.findAll();
        return ShopResponse.builder().item(listItem).build();
    }

    public ShopResponse getAllItemByCategory(Long id) {
        List<Item> listItem = itemResponsitory.findItemByCategoryId(id);
        return ShopResponse.builder().item(listItem).build();
    }

    public ShopResponse getAllItemBySearch(SearchRequest request) {
        List<Item> listItem = new ArrayList<>();
        System.out.println(request.getFilter());
        switch (request.getFilter()) {
            case "1":
                listItem = itemResponsitory.findItemsByNameByDateDesc(request.getSearch(), request.getType());
                break;
            case "2":
                listItem = itemResponsitory.findItemsByNameByDateASC(request.getSearch(), request.getType());
                break;
            case "3":
                listItem = itemResponsitory.findItemsByNameByPriceASC(request.getSearch(), request.getType());
                break;
            case "4":
                listItem = itemResponsitory.findItemsByNameByPriceDesc(request.getSearch(), request.getType());
                break;
            default:
                listItem = itemResponsitory.findItemsByName(request.getSearch(), request.getType());
                break;
        }
        return ShopResponse.builder().item(listItem).build();
    }

    public StatusResponse addItem(AddItemRequest request) {
        System.out.println(request.toString());
        TypeItem typeItem = typeItemRepository.findById(request.getType()).orElse(null);
        if (typeItem == null) {
            return StatusResponse.builder().status("fall").build();
        }
        Item item = Item.builder()
                .name(request.getName())
                .price(request.getPrice())
                .type(typeItem)
                .description(request.getDescription())
                .gender(request.getGender())
                .image(request.getImage())
                .count(0)
                .date(new Date())
                .build();
        itemResponsitory.save(item);
        return StatusResponse.builder().status("success").build();
    }

    public TypeItemResponse getTypeItem() {
        List<TypeItem> listTypeItems = typeItemRepository.findAll();
        return TypeItemResponse.builder().typeItems(listTypeItems).build();
    }

    public Item getItem(Long id) {
        return itemResponsitory.findById(id).orElse(null);
    }

    public StatusResponse editItem(Long id, EditItemRequest request) {
        TypeItem typeItem = typeItemRepository.findById(request.getType()).orElse(null);
        Item item = itemResponsitory.findById(id).orElse(null);
        if (item == null || typeItem == null) {
            return StatusResponse.builder().status("fall").build();
        }
        item.setName(request.getName());
        item.setGender(request.getGender());
        item.setType(typeItem);
        if (request.getImage() != null) {
            fileService.removeImage(item.getImage());
            item.setImage(request.getImage());
        }
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        itemResponsitory.save(item);
        return StatusResponse.builder().status("success").build();
    }

    public StatusResponse deleteItem(Long id) {
        Item item = itemResponsitory.findById(id).orElse(null);
        if (item == null) {
            return StatusResponse.builder().status("fall").build();
        }
        fileService.removeImage(item.getImage());
        itemResponsitory.delete(item);
        return StatusResponse.builder().status("success").build();
    }

    public HomeResponse getHomePage() {
        List<Item> listItem = itemResponsitory.findTopItem();
        Long amount = itemResponsitory.findMoneyByMonth();
        Long count = 0L;
        List<String> list = itemResponsitory.findIdsByMonth();
        for (String listString : list) {
            List<Long[]> ids = JsonConven.convertJsonToList(listString);
            for (Long[] id : ids) {
                count += id[1];
            }
        }
        return HomeResponse.builder().items(listItem).amount(amount).count(count).build();
    }

}
