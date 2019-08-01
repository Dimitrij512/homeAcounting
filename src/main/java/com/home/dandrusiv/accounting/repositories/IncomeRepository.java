package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Income;

public interface IncomeRepository {
    Income findByAcId(final String icId);
}
