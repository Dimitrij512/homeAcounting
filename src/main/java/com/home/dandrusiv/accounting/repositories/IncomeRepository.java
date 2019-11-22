package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Category;

public interface IncomeRepository {

    void addCategory(final Category category);
    void removeCategory(final String id);
}
