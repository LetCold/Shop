package com.phamthainguyen.website.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamthainguyen.website.model.entity.BuyHistory;
import com.phamthainguyen.website.model.entity.Cart;
import com.phamthainguyen.website.model.entity.Item;
import com.phamthainguyen.website.model.request.BuyRequest;
import com.phamthainguyen.website.model.response.StatusResponse;
import com.phamthainguyen.website.responsitory.BuyHistoryRepository;
import com.phamthainguyen.website.responsitory.CartRepository;
import com.phamthainguyen.website.responsitory.ItemRepository;
import com.phamthainguyen.website.util.JsonConven;
@Service
public class PayService {

    @Autowired
    private BuyHistoryRepository buyHistoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    public StatusResponse buy(Long id, BuyRequest request){
        List<Long[]> ids = request.getIds();
        Long totalPrice = 0L;
        for( Long[] idItem:ids){
            Item item = itemRepository.findById(idItem[0]).orElse(null);
            Cart cart = cartRepository.findByUserAndItem(id, item);
            if(item == null || cart == null){
                return StatusResponse.builder().status("fall").build();
            }
            item.setCount(item.getCount()+1);
            itemRepository.save(item);
            totalPrice += cart.getItem().getPrice() * cart.getCount();
            cartRepository.delete(cart);
        }

        BuyHistory buyHistory = BuyHistory.builder()
        .user(id)
        .location(request.getLocation())
        .numberPhone(request.getNumberPhone())
        .note(request.getNote())
        .ids(JsonConven.convertListToJson(ids))
        .amount(totalPrice)
        .date(new Date())
        .build();
        buyHistoryRepository.save(buyHistory);
        return StatusResponse.builder().status("success").build();
    }
    
}
