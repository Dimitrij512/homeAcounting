package com.home.dandrusiv.accounting.repositories;

import org.springframework.stereotype.Repository;

import com.home.dandrusiv.accounting.models.Item;

@Repository
public interface ItemRepositrory {
    Item create(Item item);
    Item update(Item subCategory);
    Item getById(String id);
    Item findByName(String name);
    void delete(String id);
}
