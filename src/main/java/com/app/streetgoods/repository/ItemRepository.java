package com.app.streetgoods.repository;

import com.app.streetgoods.model.Coordinates;
import com.app.streetgoods.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select * from item i where i.id = ?1", nativeQuery = true)
    Item findByID(Long id);

    @Query(value = "select * from item i where i.name like %?1%", nativeQuery = true)
    List<Item> findByName(String name);

    @Query(value = "select * from item i where i.item_keywords like %?1%", nativeQuery = true)
    List<Item> findByKeywords(String[] keywords);

    @Query(value = "SELECT *, ( 3959 * acos ( cos ( radians(:location.lat) ) " +
            "* cos( radians( lat ) ) * cos( radians( lng ) " +
            "- radians(:location.lng) ) " +
            "+ sin ( radians(:location.lat) ) * sin( radians( lat ) ))) " +
            "AS distance FROM items i " +
            "HAVING distance < :radius " +
            "ORDER BY distance LIMIT 0 , 20", nativeQuery = true)
    List<Item> findByLocation(@Param("location") Coordinates location,@Param("radius") Double radius);
}