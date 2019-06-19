package com.home.dandrusiv.accounting.models;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Outlay {
    List<Category> categoryList;
}
