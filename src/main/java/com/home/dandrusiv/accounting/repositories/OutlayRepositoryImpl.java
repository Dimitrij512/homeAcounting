package com.home.dandrusiv.accounting.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.dandrusiv.accounting.models.Outlay;

@Repository
public class OutlayRepositoryImpl implements OutlayRepository {

    @Autowired
    private MongoOperations operations;

    @Override
    public Outlay findByAcId(final String acId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("accountingContextId").is(acId));

        return operations.findOne(query, Outlay.class);
    }
}
