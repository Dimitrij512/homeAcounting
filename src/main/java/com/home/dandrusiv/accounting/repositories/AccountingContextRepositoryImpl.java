package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.AccountingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AccountingContextRepositoryImpl implements AccountingContextRepository {

    @Autowired
    MongoOperations operations;

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
    public List<AccountNotFoundException> findByUserId(String userId) {
        // TODO implement functionality
        return null;
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        operations.findAndRemove(query, AccountingContext.class);
    }
}
