package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.SubCategory;

public interface SubCategoryRepository {
    SubCategory create(SubCategory subCategory);
    SubCategory update(SubCategory subCategory);
    SubCategory getById(String id);
    SubCategory findByName(String name);
    void delete(String id);

}
