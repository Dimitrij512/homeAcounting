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

    @Autowired
    private MongoOperations operations;

    private String SUB_CATEGORY_DOCUMENT = "subCategory";

    @Override
    public SubCategory create(final SubCategory subCategory) {
        return operations.insert(subCategory, SUB_CATEGORY_DOCUMENT);
    }

    @Override
    public SubCategory update(SubCategory subCategory) {
        return operations.save(subCategory);
    }

    @Override
    public SubCategory getById(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, SubCategory.class);
    }

    @Override
    public SubCategory findByName(final String name) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return operations.findOne(query, SubCategory.class);
    }

    @Override
    public List<SubCategory> findByCategoryId(final String categoryId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("idCategory").is(categoryId));

        return operations.find(query, SubCategory.class);
    }

    @Override
    public void delete(String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, SubCategory.class);
    }

}
