package com.phamthainguyen.website.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditItemRequest {
    private String name;
    private byte gender;
    private Long type;
    private String description;
    private Long price;
    private String image;
}
