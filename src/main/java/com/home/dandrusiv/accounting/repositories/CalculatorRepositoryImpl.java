package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.aggregation.Sum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Date;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class CalculatorRepositoryImpl implements CalculatorRepository {
    private final String ITEM_DOCUMENT = "item";

    @Autowired
    private MongoOperations operations;


    @Override
    public double calculateSumByCategoryId(final String categoryId, Date startDate, Date endDate) {

        Aggregation agg = newAggregation(
                match(Criteria.where("categoryId")
                        .is(categoryId)
                        .and("date")
                        .gt(startDate)
                        .lte(endDate)),
                group("categoryId")
                        .sum("value").as("sum")
        );

        return operations.aggregate(agg, ITEM_DOCUMENT, Sum.class).getMappedResults()
                .stream()
                .findFirst().orElse(new Sum())
                .getSum();
    }

}
