package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Category;
import com.home.dandrusiv.accounting.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final MongoOperations operations;

    @Autowired
    public CategoryRepositoryImpl(MongoOperations operations) {

        this.operations = operations;
    }

    private String CATEGORY_DOCUMENT = "category";

    @Override
    public Category create(Category category) {

        return operations.insert(category, CATEGORY_DOCUMENT);
    }

    @Override
    public Category update(Category category) {

        return operations.save(category);
    }

    @Override
    public Category getById(String id) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, Category.class);
    }

    @Override
    public List<Category> findCategoriesByBalanceId(String balanceId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("balanceId").is(balanceId));

        return operations.find(query, Category.class);
    }

    @Override
    public void delete(String id) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, Item.class);
    }

}
