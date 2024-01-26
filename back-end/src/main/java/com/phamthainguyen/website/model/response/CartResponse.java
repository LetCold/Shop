package com.phamthainguyen.website.model.response;


import java.util.List;

import com.phamthainguyen.website.model.entity.Cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private List<Cart> cart;
}
