package com.home.dandrusiv.accounting.repositories;

import java.util.Date;

public interface CalculatorRepository {

    double getSumByCategory(String categoryId, Date startDate, Date endDate);
}
