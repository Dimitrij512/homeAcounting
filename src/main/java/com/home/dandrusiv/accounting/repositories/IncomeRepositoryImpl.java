package com.home.dandrusiv.accounting.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.dandrusiv.accounting.models.Income;

@Repository
public class IncomeRepositoryImpl implements IncomeRepository {

    @Autowired
    private MongoOperations operations;

    @Override
    public Income findByAcId(final String acId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("accountingContextId").is(acId));

        return operations.findOne(query, Income.class);
    }
}
