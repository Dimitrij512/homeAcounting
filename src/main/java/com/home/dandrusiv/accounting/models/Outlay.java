package com.home.dandrusiv.accounting.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Document
public class Outlay {
    List<Category> categoryList;
}
