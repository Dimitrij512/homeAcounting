package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.AccountingContext;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountingContextRepository {
    AccountingContext create(AccountingContext accountingContext);
    AccountingContext update(AccountingContext accountingContext);
    AccountingContext getById(String id);
    AccountingContext getByName(String name);
    List<AccountNotFoundException> findByUserId(String userId);
    void delete(String id);
}
