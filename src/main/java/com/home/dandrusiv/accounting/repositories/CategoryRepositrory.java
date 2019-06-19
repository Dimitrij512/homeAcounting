package com.home.dandrusiv.accounting.repositories;

import org.springframework.stereotype.Repository;

import com.home.dandrusiv.accounting.models.Category;

@Repository
public interface CategoryRepositrory {
    Category create(Category category);
    Category update(Category category);
    Category getById(String id);
    Category findByName(String name);
    void delete(String id);
}
