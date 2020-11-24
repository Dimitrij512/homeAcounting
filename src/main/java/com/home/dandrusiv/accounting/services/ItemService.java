package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.models.Category;
import com.home.dandrusiv.accounting.models.Item;
import com.home.dandrusiv.accounting.repositories.CategoryRepository;
import com.home.dandrusiv.accounting.repositories.ItemRepository;
import com.home.dandrusiv.accounting.repositories.OutlayRepository;
import com.home.dandrusiv.accounting.repositories.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repository;
    private final OutlayRepository outlayRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public ItemService(ItemRepository repository, OutlayRepository outlayRepository,
                       CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {

        this.repository = repository;
        this.outlayRepository= outlayRepository;
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }


    public Item create(Item item) {
        return repository.create(item);
    }

    public Item update(Item item) {
        return repository.update(item);
    }

    public Item getById(String id) {
        return repository.getById(id);
    }

    public List<Item> findItemsByCategoryId(String categoryId) {
        return repository.findItemsByCategoryId(categoryId);
    }

    public List<Item> findItemsByDate(long epochStartDate, long epochEndDate) {
        Date startDate = Date.from(Instant.ofEpochSecond(epochStartDate));
        Date endDate = Date.from(Instant.ofEpochSecond(epochEndDate));

        return repository.findItemsByDate(startDate, endDate);
    }

    public List<Item> findOutLayItemsByDate(String balanceId, long epochStartDate, long epochEndDate) {

        List<Category> categoryByBalanceId = categoryRepository.findCategoriesByBalanceId(balanceId);


        return null;
    }

    public void delete(String id) {
        repository.delete(id);
    }

}
