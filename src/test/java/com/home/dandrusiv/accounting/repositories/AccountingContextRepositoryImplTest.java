package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static com.home.dandrusiv.accounting.util.TestUtil.prepareUser;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class AccountingContextRepositoryImplTest {
    @Autowired
    private MongoOperations operations;

    @Autowired
    private AccountingContextRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OutlayRepository outlayRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CalculatorRepository calculatorRepository;

    private AccountingContext accountingContext;

    @Before
    public void setUp() {
        accountingContext = prepareAccountingContext();
    }

    @After
    public void tearDown() {
        operations.dropCollection("accountingContext");
    }

    @Test
    public void testLogic() {
        // create user
        User user = userRepository.create(prepareUser("dmytor.andrisiv@gmail.com", "Dmytro", "Andrusiv"));
        // update accounting context
        accountingContext.setUserIdList(Collections.singletonList(user.getId()));
        accountingContext.setIncome(prepareIncome(accountingContext.getId()));
        accountingContext.setOutlay(prepareOutLay(accountingContext.getId()));
        // create accounting context
        AccountingContext createdAc = repository.create(accountingContext);

        final List<Category> incomeCategories = prepareDefaultCategories(createdAc.getIncome().getId());
        final List<Category> outLayCategories = prepareDefaultCategories(createdAc.getOutlay().getId());

        Category firstIncomeCategory = incomeCategories.get(0);
        Category secondIncomeCategory = incomeCategories.get(1);
        Category firstOutlayCategory = outLayCategories.get(0);
        Category secondOutlayCategory = outLayCategories.get(1);

        // create categories
        categoryRepository.create(firstIncomeCategory);
        categoryRepository.create(secondIncomeCategory);
        categoryRepository.create(firstOutlayCategory);
        categoryRepository.create(secondOutlayCategory);

        // crate subcategories
        SubCategory firstSubCategory =  prepareSubcategory(firstIncomeCategory.getId(), "One payment");
        SubCategory firstOutlaySubCategory =  prepareSubcategory(firstOutlayCategory.getId(), "Ще одна підкатегорія");

        subCategoryRepository.create(firstSubCategory);
        subCategoryRepository.create(firstOutlaySubCategory);

        // create items for categories
        itemRepository.create(prepareItem(firstIncomeCategory.getId(), "Зарплата", 1500));
        itemRepository.create(prepareItem(secondIncomeCategory.getId(), "Аванс", 5500));

        itemRepository.create(prepareItem(firstOutlayCategory.getId(), "Овочі", 25500));
        itemRepository.create(prepareItem(firstOutlayCategory.getId(), "Фрукти", 23500.50));
        itemRepository.create(prepareItem(firstOutlayCategory.getId(), "Хліб", 252500.50));
        itemRepository.create(prepareItem(firstOutlaySubCategory.getId(), "Собака", 33.3));


        itemRepository.create(prepareItem(secondOutlayCategory.getId(), "Ще щось", 3500.25));



        Date startDate = new Date(2010, 3, 1);
        Date endDate = new Date(2019, 4, 1);
        final List<Item> itemsByDate = itemRepository.findItemsByDate(startDate, endDate);

        double sum = 25500 + 23500.50 + 252500.50 + 33.3;
        double sumCalculated = calculatorRepository.getSumByCategory(firstOutlayCategory.getId(), startDate, endDate);

        assertThat(sumCalculated).isEqualTo(sum);
    }

    private static SubCategory prepareSubcategory(String categoryId, String name) {
        SubCategory subCategory = new SubCategory();
        subCategory.setId(UUID.randomUUID().toString());
        subCategory.setCategoryId(categoryId);
        subCategory.setName(name);

        return subCategory;
    }

    private static Item prepareItem(String categoryId, String name, double value) {
        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setDate(new Date(2018, 3, 1));
        item.setCategoryId(categoryId);
        item.setName(name);
        item.setValue(value);

        return item;
    }

    @Test
    public void create() {
        AccountingContext createdAc = repository.create(accountingContext);

        assertThat(createdAc).isNotNull();
        assertThat(accountingContext).isEqualToIgnoringGivenFields(createdAc, "id");

        Outlay outlay = outlayRepository.findByAcId(createdAc.getId());

        assertThat(outlay).isNotNull();
        assertThat(outlay.getAccountingContextId()).isEqualTo(createdAc.getId());
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