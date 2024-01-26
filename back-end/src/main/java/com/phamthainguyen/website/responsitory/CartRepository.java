package com.phamthainguyen.website.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phamthainguyen.website.model.entity.Cart;
import com.phamthainguyen.website.model.entity.Item;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.user = :userId AND c.item = :item")
    Cart findByUserAndItem(@Param("userId") Long userId, @Param("item") Item item);

    @Query("SELECT c FROM Cart c WHERE c.user = :userId")
    List<Cart> findByUserId(@Param("userId") Long userId);
}
