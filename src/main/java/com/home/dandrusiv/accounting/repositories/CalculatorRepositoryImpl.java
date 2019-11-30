package com.home.dandrusiv.accounting.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.home.dandrusiv.accounting.models.aggregation.Sum;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class CalculatorRepositoryImpl implements CalculatorRepository {
    private final String ITEM_DOCUMENT = "item";

    @Autowired
    private MongoOperations operations;


    @Override
    public double calculateSumByCategoryId(final String categoryId) {
        Aggregation agg = newAggregation(
            match(Criteria.where("categoryId")
                .is(categoryId)),
            group("categoryId")
                .sum("value").as("sum")
        );

        return  operations.aggregate(agg, ITEM_DOCUMENT, Sum.class).getMappedResults()
            .stream()
            .findFirst().orElse(new Sum())
            .getSum();
    }
}
