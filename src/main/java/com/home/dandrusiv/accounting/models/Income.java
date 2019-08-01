package com.home.dandrusiv.accounting.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Income {
    @Id
    String id;
    String accountingContextId;
}
