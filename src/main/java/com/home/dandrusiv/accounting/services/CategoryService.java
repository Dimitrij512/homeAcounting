package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.models.Category;
import com.home.dandrusiv.accounting.repositories.CategoryRepository;
import com.home.dandrusiv.accounting.repositories.CategoryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category create(final Category category) {
        return repository.create(category);
    }

    public Category update(final Category category){
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
