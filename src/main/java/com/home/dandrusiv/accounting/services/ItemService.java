package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.models.Item;
import com.home.dandrusiv.accounting.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {

        this.repository = repository;
    }


    public Item create(Item item) {
        return repository.create(item);
    }

    public Item update(Item item) {
        return repository.update(item);
    }

    public Item getById(String id) {
        return repository.getById(id);
    }

    public List<Item> findItemsByCategoryId(String categoryId) {
        return repository.findItemByCategoryId(categoryId);
    }

    public List<Item> findItemsByDate(long epochStartDate, long epochEndDate) {
        Date startDate = Date.from(Instant.ofEpochSecond(epochStartDate));
        Date endDate = Date.from(Instant.ofEpochSecond(epochEndDate));

        return repository.findItemsByDate(startDate, endDate);
    }

    public void delete(String id) {
        repository.delete(id);
    }

}
