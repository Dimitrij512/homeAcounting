package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Item;

public interface ItemRepositrory {
    Item create(Item item);
    Item update(Item item);
    Item getById(String id);
    Item findByName(String name);
    void delete(String id);
}
