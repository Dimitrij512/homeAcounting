package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.models.AccountingContext;
import com.home.dandrusiv.accounting.repositories.AccountingContextRepository;
import com.home.dandrusiv.accounting.repositories.IncomeRepository;
import com.home.dandrusiv.accounting.repositories.OutlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingContextService {

    private final AccountingContextRepository accountingContextRepository;

    private final OutlayRepository outlayRepository;

    private final IncomeRepository incomeRepository;

    @Autowired
    public  AccountingContextService (AccountingContextRepository accountingContextRepository,
                                      OutlayRepository outlayRepository,
                                      IncomeRepository incomeRepository) {

        this.accountingContextRepository = accountingContextRepository;
        this.outlayRepository = outlayRepository;
        this.incomeRepository = incomeRepository;
    }

    public AccountingContext create(AccountingContext accountingContext) {
        accountingContextRepository.create(accountingContext);

        return accountingContextRepository.create(accountingContext);
    }

    public AccountingContext update(AccountingContext accountingContext){
        return accountingContextRepository.update(accountingContext);
    }

    public AccountingContext getById(String id) {
        return accountingContextRepository.getById(id);
    }

    public List<AccountingContext> findAll() {
        return accountingContextRepository.findAll();
    }

    public List<AccountingContext> findByUserId(String id) {
        return accountingContextRepository.findByUserId(id);
    }

    public void delete(String id) {
        accountingContextRepository.delete(id);
    }

}
