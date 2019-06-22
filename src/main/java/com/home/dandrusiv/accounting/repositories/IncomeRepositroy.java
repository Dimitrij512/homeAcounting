package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Category;

public interface IncomeRepositroy {

    void addCategory(final Category category);
    void removeCategory(final String id);
}
