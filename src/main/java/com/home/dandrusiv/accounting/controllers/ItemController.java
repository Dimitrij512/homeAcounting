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

import com.home.dandrusiv.accounting.models.Item;
import com.home.dandrusiv.accounting.services.ItemService;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody final Item items) {
        return service.create(items);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Item update(@RequestBody final Item items) {
        return service.update(items);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Item getById(@PathVariable final String id) {
        return service.getById(id);
    }

    @GetMapping("/categoryId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getByCategoryId(@PathVariable final String id) {
        return service.findItemsByCategoryId(id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable final String id) {
        service.delete(id);
    }

}
