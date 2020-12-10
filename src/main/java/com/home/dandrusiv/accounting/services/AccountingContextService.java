package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.models.AccountingContext;
import com.home.dandrusiv.accounting.repositories.AccountingContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingContextService {

    private final AccountingContextRepository repository;

    @Autowired
    public  AccountingContextService (AccountingContextRepository repository) {
        this.repository = repository;
    }

    public AccountingContext create(AccountingContext accountingContext) {
        return repository.create(accountingContext);
    }

    public AccountingContext update(AccountingContext accountingContext){
        return repository.update(accountingContext);
    }

    public AccountingContext getById(String id) {
        return repository.getById(id);
    }

    public List<AccountingContext> findAll() {
        return repository.findAll();
    }

    public List<AccountingContext> findByUserId(String id) {
        return repository.findByUserId(id);
    }

    public void delete(String id) {
        repository.delete(id);
    }

}
