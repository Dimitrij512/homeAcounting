package com.home.dandrusiv.accounting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.home.dandrusiv.accounting.models.SubCategory;
import com.home.dandrusiv.accounting.services.SubCategoryService;

@RestController
@RequestMapping("subCategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubCategory create(@RequestBody final SubCategory subCategory) {
        return service.create(subCategory);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SubCategory update(@RequestBody final SubCategory subCategory) {
        return service.update(subCategory);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubCategory getById(@PathVariable final String id) {
        return service.getById(id);
    }

    @GetMapping("/categoryId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<SubCategory> findByCategoryId(@PathVariable final String id) {
        return service.findByCategoryId(id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable final String id) {
        service.delete(id);
    }

}
