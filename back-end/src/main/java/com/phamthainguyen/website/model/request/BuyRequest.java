package com.phamthainguyen.website.model.request;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NoRepositoryBean
@Builder
public class BuyRequest {

    private String location;

    private String numberPhone;

    private String note;

    private List<Long[]> ids;

    private Long amount;

}
