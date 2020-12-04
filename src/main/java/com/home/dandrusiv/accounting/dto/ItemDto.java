package com.home.dandrusiv.accounting.dto;

import java.util.Date;

import lombok.Builder;

@Builder
public class ItemDto {
    private String id;
    private String name;
    private double value;
    private String comment;
    Date date;
    String categoryId;
    String categoryName;
}
