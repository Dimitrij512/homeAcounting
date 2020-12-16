package com.home.dandrusiv.accounting.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemDto {
    private final String id;
    private final String name;
    private final double value;
    private final String comment;
    private final String date;
    private final String categoryId;
    private final String categoryName;
}
