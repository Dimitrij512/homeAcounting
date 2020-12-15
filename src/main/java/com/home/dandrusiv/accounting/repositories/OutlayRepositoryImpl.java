package com.home.dandrusiv.accounting.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.home.dandrusiv.accounting.models.Outlay;

@Repository
public class OutlayRepositoryImpl implements OutlayRepository {

    final MongoOperations operations;

    @Autowired
    public OutlayRepositoryImpl(MongoOperations operations) {

        this.operations = operations;
    }

    @Override
    public Outlay findByAcId(String acId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountingContextId").is(acId));

        return operations.findOne(query, Outlay.class);
    }

    public Outlay insert(Outlay outlay) {
        return operations.insert(outlay, "outlay");
    }

}
