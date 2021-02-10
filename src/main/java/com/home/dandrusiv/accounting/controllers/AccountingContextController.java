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

import com.home.dandrusiv.accounting.models.AccountingContext;
import com.home.dandrusiv.accounting.services.AccountingContextService;

@RestController
@RequestMapping("accountingContext")
public class AccountingContextController {

    private AccountingContextService service;

    @Autowired
    public AccountingContextController(AccountingContextService service) {

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountingContext create(
            @RequestBody
                    AccountingContext accountingContext) {

        return service.create(accountingContext);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountingContext update(
            @RequestBody
                    AccountingContext accountingContext) {

        return service.update(accountingContext);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountingContext getById(
            @PathVariable
                    String id) {

        return service.getById(id);
    }

    @GetMapping("/userId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountingContext> findByUserId(
            @PathVariable
                    String id) {

        return service.findByUserId(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountingContext> findAll() {

        return service.findAll();
    }

    @DeleteMapping("/id/{id}")
    public void delete(
            @PathVariable
                    String id) {

        service.delete(id);
    }

}
