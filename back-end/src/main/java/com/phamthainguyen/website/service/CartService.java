package com.phamthainguyen.website.service;

import com.phamthainguyen.website.model.entity.Cart;
import com.phamthainguyen.website.model.entity.Item;
import com.phamthainguyen.website.model.entity.User;
import com.phamthainguyen.website.model.request.ItemRequest;
import com.phamthainguyen.website.model.response.CartResponse;
import com.phamthainguyen.website.model.response.StatusResponse;
import com.phamthainguyen.website.responsitory.CartRepository;
import com.phamthainguyen.website.responsitory.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ItemService itemService;

  public StatusResponse addItem(String email, Long id, Long count) {
    User user = userRepository.findByEmail(email);
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

  public StatusResponse takeItem(String email, Long id, Long count) {
    User user = userRepository.findByEmail(email);
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

  public CartResponse getAllItemCart(String email) {
    User user = userRepository.findByEmail(email);
    List<Cart> list = cartRepository.findByUserId(user.getId());
    return CartResponse.builder().cart(list).build();
  }

  public StatusResponse deleteItemCart(String email, Long id) {
    User user = userRepository.findByEmail(email);
    Item item = itemService.getItem(id);
    Cart cart = cartRepository.findByUserAndItem(user.getId(), item);
    if (cart == null) {
      return StatusResponse.builder().status("fall").build();
    }
    cartRepository.delete(cart);
    return StatusResponse.builder().status("success").build();
  }
}
