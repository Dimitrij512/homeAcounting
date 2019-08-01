package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepositrory{

    @Autowired
    private MongoOperations operations;

    private String ITEM_DOCUMENT = "item";


    @Override
    public Item create(final Item item) {
        return operations.insert(item, ITEM_DOCUMENT);
    }

    @Override
    public Item update(final Item item) {
        return operations.save(item);
    }

    @Override
    public Item getById(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, Item.class);
    }

    @Override
    public List<Item> findItemByCategoryId(String categoryId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").is(categoryId));

        return operations.find(query, Item.class);
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, Item.class);
    }
}
