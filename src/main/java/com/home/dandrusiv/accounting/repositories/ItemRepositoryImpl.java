package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ItemRepositoryImpl implements ItemRepositrory{

    @Autowired
    MongoOperations operations;

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
    public Item findByName(final String name) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return operations.findOne(query, Item.class);
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, Item.class);
    }
}
