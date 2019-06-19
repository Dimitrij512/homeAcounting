package com.home.dandrusiv.accounting.repositories;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Repository;

import com.home.dandrusiv.accounting.models.AccountingContext;

@Repository
public interface AccountingContextRepository {
    AccountingContext create(AccountingContext accountingContext);
    AccountingContext update(AccountingContext accountingContext);
    AccountingContext getById(String id);
    List<AccountingContext> getByName(String name);
    List<AccountNotFoundException> findByUserId(String userId);
}
