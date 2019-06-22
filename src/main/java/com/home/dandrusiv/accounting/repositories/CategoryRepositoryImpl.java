package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Category;
import com.home.dandrusiv.accounting.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryRepositoryImpl implements CategoryRepositrory {

    @Autowired
    private MongoOperations operations;

    private String CATEGORY_DOCUMENT = "category";

    @Override
    public Category create(final Category category) {
        return operations.insert(category, CATEGORY_DOCUMENT);
    }

    @Override
    public Category update(final Category category) {
        return operations.save(category);
    }

    @Override
    public Category getById(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, Category.class);
    }

    @Override
    public List<Category> findCategoryByBalanceId(final String balanceId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("idBalance").is(balanceId));

        return operations.find(query, Category.class);
    }

    @Override
    public Category findByName(final String name) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return operations.findOne(query, Category.class);
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, Item.class);
    }
}
