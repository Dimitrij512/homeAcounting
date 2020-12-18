package com.home.dandrusiv.accounting.repositories;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.home.dandrusiv.accounting.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final MongoOperations operations;

    @Autowired
    public ItemRepositoryImpl(MongoOperations operations) {

        this.operations = operations;
    }

    private String ITEM_DOCUMENT = "item";

    @Override
    public Item create(Item item) {

        item.setEpochTime(Instant.now().toEpochMilli());
        return operations.insert(item, ITEM_DOCUMENT);
    }

    @Override
    public Item update(Item item) {

        item.setEpochTime(Instant.now().toEpochMilli());
        return operations.save(item);
    }

    @Override
    public Item getById(String id) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, Item.class);
    }

    @Override
    public List<Item> findItemsByCategoryId(String categoryId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").is(categoryId));

        return operations.find(query, Item.class);
    }

    @Override
    public List<Item> findItemsByDate(Date startDate, Date endDate) {

        Query query = new Query();
        Criteria.where("id").exists(true)
                .and("date")
                .gte(startDate)
                .lte(endDate);

        return operations.find(query, Item.class);
    }

    public List<Item> findItemsByCategoryIdAndDate(String categoryId, long epochStartDate, long epochEndDate) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").exists(true)
                                  .and("categoryId")
                                  .is(categoryId)
                                  .and("date")
                                  .gte(epochStartDate)
                                  .lte(epochEndDate));

        return operations.find(query, Item.class);
    }

    public List<Item> findItemsByCategoryIdsAndDate(List<String> categoryIds, long epochStartDate, long epochEndDate) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").exists(true)
                                  .and("categoryId")
                                  .in(categoryIds)
                                  .and("epochTime")
                                  .gte(epochStartDate)
                                  .lte(epochEndDate));

        return operations.find(query, Item.class);
    }

    @Override
    public void delete(String id) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, Item.class);
    }

}
