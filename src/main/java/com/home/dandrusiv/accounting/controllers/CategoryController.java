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

import com.home.dandrusiv.accounting.models.Category;
import com.home.dandrusiv.accounting.services.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody final Category category) {
        return service.create(category);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Category update(@RequestBody final Category category) {
        return service.update(category);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getById(@PathVariable final String id) {
        return service.getById(id);
    }

    @GetMapping("/balanceId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findCategoryByBalanceId(@PathVariable final String id) {
        return service.findCategoryByBalanceId(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Category findByName(@PathVariable final String name) {
        return service.findByName(name);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable final String id) {
        service.delete(id);
    }

}
