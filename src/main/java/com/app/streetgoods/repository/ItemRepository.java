package com.app.streetgoods.repository;

import com.app.streetgoods.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select * from item i where i.id = ?1", nativeQuery = true)
    Item findByID(Long id);

    @Query(value = "select * from item i where i.name like %?1% or i.item_keywords like %?1%",
            nativeQuery = true)
    List<Item> findByNameOrKeywords(String name);

    @Query(value = "SELECT *, ( 3959 * acos ( cos ( radians(:lat) ) " +
            "* cos( radians( lat ) ) * cos( radians( lng ) " +
            "- radians(:lng) ) " +
            "+ sin ( radians(:lat) ) * sin( radians( lat ) ))) " +
            "AS distance FROM item i " +
            "HAVING distance < :rad " +
            "ORDER BY distance LIMIT 0 , 20", nativeQuery = true)
    List<Item> findByLocation(@Param("lat") Double lat,@Param("lng") Double lng, @Param("rad") Double radius);
}