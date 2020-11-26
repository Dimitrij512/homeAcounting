package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.aggregation.CollectionCategoryId;
import com.home.dandrusiv.accounting.models.aggregation.Sum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class CalculatorRepositoryImpl implements CalculatorRepository {
    private final String ITEM_DOCUMENT = "item";

    private final String SUB_CATEGORY_DOCUMENT = "subCategory";

    private MongoOperations operations;

    @Autowired
    public CalculatorRepositoryImpl(MongoOperations operations){
        this.operations = operations;
    }

    @Override
    public double getSumByCategory(String categoryId, Date startDate, Date endDate) {
        double sumByCategory = calculateSumByCategoryId(categoryId, startDate, endDate);
        double sumByAllSubCategory = getSubCategoryIdsByCategoryId(categoryId)
                .stream()
                .mapToDouble(subCategoryId -> calculateSumByCategoryId(subCategoryId, startDate, endDate))
                .sum();

        return sumByCategory + sumByAllSubCategory;
    }

    private double calculateSumByCategoryId(String categoryId, Date startDate, Date endDate) {

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

    private List<String> getSubCategoryIdsByCategoryId(String categoryId) {
        Aggregation agg = newAggregation(
                match(Criteria
                        .where("categoryId")
                        .is(categoryId)),
                group().addToSet("_id").as("listIds"));

        return operations.aggregate(agg, SUB_CATEGORY_DOCUMENT, CollectionCategoryId.class)
                .getUniqueMappedResult()
                .getListIds();
    }

}
