package com.phamthainguyen.website.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phamthainguyen.website.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

    @Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :search, '%')) AND (:typeid IS NULL OR i.type.id = :typeid)")
    List<Item> findItemsByName(@Param("search") String search, @Param("typeid") String typeId);

    @Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :search, '%')) AND (:typeid < 1  OR i.type.id  = :typeid) ORDER BY i.date DESC")
    List<Item> findItemsByNameByDateDesc(@Param("search") String search, @Param("typeid") String typeId);

    @Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :search, '%')) AND (:typeid < 1 OR i.type.id = :typeid) ORDER BY i.date ASC")
    List<Item> findItemsByNameByDateASC(@Param("search") String search, @Param("typeid") String typeId);

    @Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :search, '%')) AND (:typeid < 1 OR i.type.id = :typeid) ORDER BY i.price DESC")
    List<Item> findItemsByNameByPriceDesc(@Param("search") String search, @Param("typeid") String typeId);

    @Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :search, '%')) AND (:typeid < 1 OR i.type.id = :typeid) ORDER BY i.price ASC")
    List<Item> findItemsByNameByPriceASC(@Param("search") String search, @Param("typeid") String typeId);

    @Query("SELECT i FROM Item i WHERE i.type.id = :id")
    List<Item> findItemByCategoryId(@Param("id") Long id);

    @Query("SELECT i FROM Item i ORDER BY i.count DESC LIMIT 10")
    List<Item> findTopItem();

    @Query("SELECT SUM(bh.amount) FROM BuyHistory bh WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND YEAR(date) = YEAR(CURRENT_DATE())")
    Long findMoneyByMonth();

    @Query("SELECT bh.ids FROM BuyHistory bh WHERE MONTH(date) = MONTH(CURRENT_DATE()) AND YEAR(date) = YEAR(CURRENT_DATE())")
    List<String> findIdsByMonth();
}
