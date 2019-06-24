package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.models.Item;
import com.home.dandrusiv.accounting.repositories.ItemRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepositoryImpl repository;


    public Item create(final Item item) {
        return repository.create(item);
    }

    public Item update(final Item item) {
        return repository.update(item);
    }

    public Item getById(final String id) {
        return repository.getById(id);
    }

    public List<Item> findItemsByCategoryId(final String categoryId) {
        return repository.findItemByCategoryId(categoryId);
    }

    public void delete(final String id) {
        repository.delete(id);
    }

}
