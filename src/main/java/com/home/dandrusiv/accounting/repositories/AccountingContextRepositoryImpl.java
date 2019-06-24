package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.AccountingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class AccountingContextRepositoryImpl implements AccountingContextRepository {

    @Autowired
    private MongoOperations operations;

    private String ACCOUNTING_CONTEXT_DOCUMENT = "accountingContext";

    @Override
    public AccountingContext create(final AccountingContext accountingContext) {
        return operations.insert(accountingContext, ACCOUNTING_CONTEXT_DOCUMENT);
    }

    @Override
    public AccountingContext update(final AccountingContext accountingContext) {
        return operations.save(accountingContext);
    }

    @Override
    public AccountingContext getById(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, AccountingContext.class);
    }

    @Override
    public AccountingContext getByName(final String name) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return operations.findOne(query, AccountingContext.class);
    }

    @Override
    public List<AccountingContext> findAll() {
        return operations.findAll(AccountingContext.class);
    }

    @Override
    public List<AccountingContext> findByUserId(final String userId) {
        Aggregation agg = newAggregation(match(Criteria.where("userIdList").is(userId)));

        AggregationResults<AccountingContext> result =
                operations.aggregate(agg, ACCOUNTING_CONTEXT_DOCUMENT, AccountingContext.class);

        return result.getMappedResults();
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, AccountingContext.class);
    }

}
