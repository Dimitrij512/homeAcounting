package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Item;

import java.util.List;

public interface ItemRepositrory {
    Item create(Item item);
    Item update(Item item);
    Item getById(String id);
    List<Item> findItemByCategoryId(String categoryId);
    void delete(String id);
}
