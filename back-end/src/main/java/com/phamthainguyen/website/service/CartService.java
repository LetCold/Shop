package com.phamthainguyen.website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phamthainguyen.website.model.entity.Cart;
import com.phamthainguyen.website.model.entity.Item;
import com.phamthainguyen.website.model.entity.User;
import com.phamthainguyen.website.model.response.CartResponse;
import com.phamthainguyen.website.model.response.StatusResponse;
import com.phamthainguyen.website.responsitory.CartRepository;

@Service
public class CartService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private ItemService itemService;

  public StatusResponse addItem(User user, Long id, Long count) {
    Item item = itemService.getItem(id);
    Cart existingCartItem = cartRepository.findByUserAndItem(
        user.getId(),
        item);
    if (existingCartItem != null) {
      existingCartItem.setCount(count);
      cartRepository.save(existingCartItem);
    } else {
      Cart newCartItem = Cart
          .builder()
          .item(item)
          .user(user.getId())
          .count(count)
          .build();
      cartRepository.save(newCartItem);
    }
    return StatusResponse.builder().status("success").build();
  }

  public StatusResponse takeItem(User user, Long id, Long count) {
    Item item = itemService.getItem(id);
    Cart existingCartItem = cartRepository.findByUserAndItem(
        user.getId(),
        item);
    if (existingCartItem != null) {
      existingCartItem.setCount(existingCartItem.getCount() - count);
      cartRepository.save(existingCartItem);
      return StatusResponse.builder().status("success").build();
    }
    return StatusResponse.builder().status("fall").build();
  }

  public CartResponse getAllItemCart(User user) {
    List<Cart> list = cartRepository.findByUserId(user.getId());
    return CartResponse.builder().cart(list).build();
  }

  public StatusResponse deleteItemCart(User user, Long id) {
    Item item = itemService.getItem(id);
    Cart cart = cartRepository.findByUserAndItem(user.getId(), item);
    if (cart == null) {
      return StatusResponse.builder().status("fall").build();
    }
    cartRepository.delete(cart);
    return StatusResponse.builder().status("success").build();
  }
}
