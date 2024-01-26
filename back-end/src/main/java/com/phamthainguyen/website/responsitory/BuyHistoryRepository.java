package com.phamthainguyen.website.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamthainguyen.website.model.entity.BuyHistory;

public interface BuyHistoryRepository extends JpaRepository<BuyHistory, Long>{
    
}
