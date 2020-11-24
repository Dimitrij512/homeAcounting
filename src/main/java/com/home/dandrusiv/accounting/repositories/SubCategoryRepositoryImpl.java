package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubCategoryRepositoryImpl implements SubCategoryRepository {

    private final MongoOperations operations;

    @Autowired
    public SubCategoryRepositoryImpl(MongoOperations operations) {

        this.operations = operations;
    }

    private String SUB_CATEGORY_DOCUMENT = "subCategory";

    @Override
    public SubCategory create(SubCategory subCategory) {

        return operations.insert(subCategory, SUB_CATEGORY_DOCUMENT);
    }

    @Override
    public SubCategory update(SubCategory subCategory) {

        return operations.save(subCategory);
    }

    @Override
    public SubCategory getById(String id) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, SubCategory.class);
    }

    @Override
    public List<SubCategory> findByCategoryId(String categoryId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").is(categoryId));

        return operations.find(query, SubCategory.class);
    }

    @Override
    public List<SubCategory> findByCategoryIds(List<String> categoryId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").in(categoryId));

        return operations.find(query, SubCategory.class);
    }

    @Override
    public void delete(String id) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, SubCategory.class);
    }

}
