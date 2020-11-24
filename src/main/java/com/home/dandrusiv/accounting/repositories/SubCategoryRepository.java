package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.SubCategory;

import java.util.List;

public interface SubCategoryRepository {
    SubCategory create(SubCategory subCategory);
    SubCategory update(SubCategory subCategory);
    SubCategory getById(String id);
    List<SubCategory> findByCategoryId(String categoryId);
    List<SubCategory> findByCategoryIds(List<String> categoryIds);
    void delete(String id);

}
