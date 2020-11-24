package com.home.dandrusiv.accounting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("item")
public class ItemController {

    private final ItemService service;

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody Item item) {
        return service.create(item);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Item update(@RequestBody Item items) {
        return service.update(items);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Item getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/categoryId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getByCategoryId(@PathVariable String id) {
        return service.findItemsByCategoryId(id);
    }

    @GetMapping("/start/{epochStartDate}/end/{epochEndDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getByDate(@PathVariable long epochStartDate, @PathVariable long epochEndDate) {
        return service.findItemsByDate (epochStartDate, epochEndDate);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}
