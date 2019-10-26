package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Category;

import java.util.List;

public interface CategoryRepository {
    Category create(Category category);
    Category update(Category category);
    Category getById(String id);
    Category findByName(String name);
    List<Category> findCategoryByBalanceId(String balanceId);
    void delete(String id);
}
