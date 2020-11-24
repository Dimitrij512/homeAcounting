package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Item;

import java.util.Date;
import java.util.List;

public interface ItemRepository {
    Item create(Item item);
    Item update(Item item);
    Item getById(String id);
    List<Item> findItemsByCategoryId(String categoryId);
    List<Item> findItemsByDate(Date startDate, Date endDate);
    List<Item> findItemsByCategoryIdAndDate(String categoryId, Date startDate, Date endDate);
    void delete(String id);
}
