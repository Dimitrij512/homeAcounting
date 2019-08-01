package com.home.dandrusiv.accounting.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class AccountingContext {
    @Id
    String id;
    String name;
    List<String> userIdList;
}
