package com.home.dandrusiv.accounting.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.home.dandrusiv.accounting.models.AccountingContext;
import com.home.dandrusiv.accounting.models.Category;
import com.home.dandrusiv.accounting.models.Income;
import com.home.dandrusiv.accounting.models.Outlay;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class AccountingContextRepositoryImplTest {

    @Autowired
    private AccountingContextRepository repository;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void create() {
        AccountingContext accountingContext = prepareAccountingContext();
        AccountingContext createdAx = repository.create(accountingContext);

        assertThat(createdAx).isNotNull();

        printJson(createdAx);

        assertThat(accountingContext).isEqualToIgnoringGivenFields(createdAx, "id");


    }

    @Test
    public void update() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getByName() {
    }

    @Test
    public void findByUserId() {
    }

    @Test
    public void delete() {
    }

    private AccountingContext prepareAccountingContext() {
        AccountingContext accountingContext = new AccountingContext();

        accountingContext.setId(UUID.randomUUID().toString());
        accountingContext.setName("My accounting");

        Income income = prepareIncome(accountingContext.getId());
        income.setCategoryList(prepareDefaultCategories(income.getId()));

        Outlay outlay = prepareOutLay(accountingContext.getId());
        outlay.setCategoryList(prepareDefaultCategories(outlay.getId()));

        accountingContext.setIncome(income);
        accountingContext.setOutlay(outlay);


        return accountingContext;
    }

    private Income prepareIncome(String accountingContextId) {
        Income income = new Income();
        income.setId(UUID.randomUUID().toString());
        income.setIdAccountingContext(accountingContextId);

        return income;
    }

    private Outlay prepareOutLay(String accountingContextId) {
        Outlay outlay = new Outlay();
        outlay.setId(UUID.randomUUID().toString());
        outlay.setIdAccountingContext(accountingContextId);

        return outlay;
    }

    private List<Category> prepareDefaultCategories(String id) {
        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setIdBalance(id);
        category.setName("FirstCategory");

        Category category2 = new Category();
        category2.setId(UUID.randomUUID().toString());
        category2.setIdBalance(id);
        category2.setName("SecondCategory");

        return Arrays.asList(category, category2);
    }

    private static void printJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(object));
    }
}