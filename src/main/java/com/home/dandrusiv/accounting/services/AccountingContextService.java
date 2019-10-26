package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.models.AccountingContext;
import com.home.dandrusiv.accounting.repositories.AccountingContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingContextService {

    @Autowired
    private AccountingContextRepository repository;

    public AccountingContext create(final AccountingContext accountingContext) {
        return repository.create(accountingContext);
    }

    public AccountingContext update(final AccountingContext accountingContext){
        return repository.update(accountingContext);
    }

    public AccountingContext getById(final String id) {
        return repository.getById(id);
    }

    public List<AccountingContext> findAll() {
        return repository.findAll();
    }

    public List<AccountingContext> findByUserId(final String id) {
        return repository.findByUserId(id);
    }

    public void delete(final String id) {
        repository.delete(id);
    }

}
