package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.models.Category;
import com.home.dandrusiv.accounting.repositories.CategoryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryService {

    @Autowired
    private CategoryRepositoryImpl repository;

    public Category createCategory(final Category category) {
        return repository.create(category);
    }

    public Category updateCategory(final Category category){
        return repository.update(category);
    }

    public Category getById(final String id) {
        return repository.getById(id);
    }

    public Category findByName(String name) {
        return repository.findByName(name);
    }

    public List<Category> findCategoryByBalanceId(final String id) {
        return repository.findCategoryByBalanceId(id);
    }

    public void delete(final String id) {
        repository.delete(id);
    }

}
