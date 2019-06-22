package com.home.dandrusiv.accounting.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Income {
    List<Category> categoryList;
}
