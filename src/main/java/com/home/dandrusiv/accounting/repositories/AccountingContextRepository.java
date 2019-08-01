package com.home.dandrusiv.accounting.repositories;

import java.util.List;

import com.home.dandrusiv.accounting.models.AccountingContext;

public interface AccountingContextRepository {
    AccountingContext create(AccountingContext accountingContext);
    AccountingContext update(AccountingContext accountingContext);
    AccountingContext getById(String id);
    List<AccountingContext> findAll();
    List<AccountingContext> findByUserId(String userId);
    void delete(String id);
}
