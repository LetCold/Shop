package com.phamthainguyen.website.model.response;

import java.util.List;

import com.phamthainguyen.website.model.entity.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeResponse {

    private Long amount;

    private Long count;

    private List<Item> items;
    
}
