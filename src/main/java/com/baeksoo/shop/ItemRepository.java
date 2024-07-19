package com.baeksoo.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer>{

    Optional<Item> findById(Long id);
    List<Item> findAllByTitleContains(String title);

    @Query(value = "SELECT * FROM shop.item WHERE MATCH(title) AGAINST(?1)",  nativeQuery = true)
    List<Item> rawQuery1(String text);
}
