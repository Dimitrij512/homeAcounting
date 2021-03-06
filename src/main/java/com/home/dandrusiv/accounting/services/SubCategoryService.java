package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.models.SubCategory;
import com.home.dandrusiv.accounting.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository repository;

    public SubCategory create(final SubCategory category) {
        return repository.create(category);
    }

    public SubCategory update(final SubCategory category){
        return repository.update(category);
    }

    public SubCategory getById(final String id) {
        return repository.getById(id);
    }

    public List<SubCategory> findByCategoryId(final String id) {
        return repository.findByCategoryId(id);
    }

    public void delete(final String id) {
        repository.delete(id);
    }

}
