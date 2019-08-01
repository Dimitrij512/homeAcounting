package com.home.dandrusiv.accounting.repositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.home.dandrusiv.accounting.models.AccountingContext;
import com.home.dandrusiv.accounting.models.Category;
import com.home.dandrusiv.accounting.models.Income;
import com.home.dandrusiv.accounting.models.Outlay;
import com.home.dandrusiv.accounting.models.User;

import static org.assertj.core.api.Assertions.assertThat;

import static com.home.dandrusiv.accounting.util.TestUtil.prepareUser;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class AccountingContextRepositoryImplTest {

    @Autowired
    MongoOperations operations;

    @Autowired
    private AccountingContextRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OutlayRepository outlayRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    AccountingContext accountingContext;

    @Before
    public void setUp() {
        accountingContext = prepareAccountingContext();
    }

    @After
    public void tearDown() {
        operations.dropCollection("accountingContext");
    }

    @Test
    public void create() {
        AccountingContext createdAc = repository.create(accountingContext);

        assertThat(createdAc).isNotNull();
        assertThat(accountingContext).isEqualToIgnoringGivenFields(createdAc, "id");

        Outlay outlay = outlayRepository.findByAcId(createdAc.getId());

        assertThat(outlay).isNotNull();
        assertThat(outlay.getAccountingContextId()).isEqualTo(createdAc.getId());

        Income income = incomeRepository.findByAcId(createdAc.getId());

        assertThat(income).isNotNull();
        assertThat(income.getAccountingContextId()).isEqualTo(createdAc.getId());
    }

    @Test
    public void update() {
        AccountingContext createdAc = repository.create(accountingContext);
        createdAc.setName("UpdatedAccounting");

        AccountingContext updatedAx = repository.update(createdAc);

        assertThat(updatedAx).isNotNull();
        assertThat(createdAc).isEqualToIgnoringGivenFields(updatedAx,  "name");
        assertThat(createdAc).isNotEqualTo(updatedAx.getName());
    }

    @Test
    public void getById() {
        AccountingContext createdAc = repository.create(accountingContext);
        AccountingContext axById = repository.getById(createdAc.getId());

        assertThat(createdAc).isEqualTo(axById);
    }

    @Test
    public void findByUserId() {
        User testUser1 = userRepository.create(prepareUser("test@email.com", "Test1Name", "TestLast1Name"));
        User testUser2 = userRepository.create(prepareUser("test@email.com", "Test2Name", "TestLast2Name"));
        accountingContext.setUserIdList(Arrays.asList(testUser1.getId(), testUser2.getId()));

        AccountingContext createdAx = repository.create(accountingContext);

        List<AccountingContext> listAkByUserId1 = repository.findByUserId(testUser1.getId());
        List<AccountingContext> listAkByUserId2 = repository.findByUserId(testUser2.getId());

        assertThat(listAkByUserId1).isNotEmpty();
        assertThat(listAkByUserId1).hasSize(1);
        assertThat(listAkByUserId1.get(0)).isEqualTo(createdAx);
        assertThat(listAkByUserId1).isEqualTo(listAkByUserId2);
    }

    @Test
    public void findFewAcByUserId() {
        User testUser1 = userRepository.create(prepareUser("test@email.com", "Test1Name", "TestLast1Name"));

        accountingContext.setUserIdList(Collections.singletonList(testUser1.getId()));

        //create two accounting context
        AccountingContext createdAx1 = repository.create(accountingContext);
        //changed id of accounting context
        accountingContext.setId(UUID.randomUUID().toString());
        AccountingContext createdAx2 = repository.create(accountingContext);

        List<AccountingContext> listAkByUserId = repository.findByUserId(testUser1.getId());

        assertThat(listAkByUserId).isNotEmpty();
        assertThat(listAkByUserId).hasSize(2);
        assertThat(listAkByUserId).contains(createdAx1, createdAx2);
    }

    @Test
    public void delete() {
        AccountingContext ac = repository.create(prepareAccountingContext());
        assertThat(ac).isNotNull();

        AccountingContext acFindById = repository.getById(ac.getId());
        assertThat(acFindById).isNotNull();
        assertThat(ac).isEqualTo(acFindById);

        repository.delete(acFindById.getId());

        assertThat(repository.findAll()).isEmpty();
    }

    private AccountingContext prepareAccountingContext() {
        AccountingContext accountingContext = new AccountingContext();

        accountingContext.setId(UUID.randomUUID().toString());
        accountingContext.setName("My accounting");

        return accountingContext;
    }

    private Income prepareIncome(String accountingContextId) {
        Income income = new Income();
        income.setId(UUID.randomUUID().toString());
        income.setAccountingContextId(accountingContextId);

        return income;
    }

    private Outlay prepareOutLay(String accountingContextId) {
        Outlay outlay = new Outlay();
        outlay.setId(UUID.randomUUID().toString());
        outlay.setAccountingContextId(accountingContextId);

        return outlay;
    }

    private List<Category> prepareDefaultCategories(String id) {
        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setBalanceId(id);
        category.setName("FirstCategory");

        Category category2 = new Category();
        category2.setId(UUID.randomUUID().toString());
        category2.setBalanceId(id);
        category2.setName("SecondCategory");

        return Arrays.asList(category, category2);
    }

}