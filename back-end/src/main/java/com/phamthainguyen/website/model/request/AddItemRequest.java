package com.phamthainguyen.website.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemRequest {
    private String name;
    private byte gender;
    private Long type;
    private String description;
    private Long price;
    private String image;
}
