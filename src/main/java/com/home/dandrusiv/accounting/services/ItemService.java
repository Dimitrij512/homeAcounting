package com.home.dandrusiv.accounting.services;

import com.home.dandrusiv.accounting.dto.ItemDto;
import com.home.dandrusiv.accounting.models.Category;
import com.home.dandrusiv.accounting.models.Item;
import com.home.dandrusiv.accounting.models.SubCategory;
import com.home.dandrusiv.accounting.repositories.CategoryRepository;
import com.home.dandrusiv.accounting.repositories.ItemRepository;
import com.home.dandrusiv.accounting.repositories.OutlayRepository;
import com.home.dandrusiv.accounting.repositories.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final OutlayRepository outlayRepository;

    private final CategoryRepository categoryRepository;

    private final SubCategoryRepository subCategoryRepository;

    private final SimpleDateFormat dateFormatter;

    public ItemService(ItemRepository itemRepository, OutlayRepository outlayRepository,
                       CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {

        this.itemRepository = itemRepository;
        this.outlayRepository = outlayRepository;
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;

        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    }

    public Item create(Item item) {

        return itemRepository.create(item);
    }

    public Item update(Item item) {

        return itemRepository.update(item);
    }

    public Item getById(String id) {

        return itemRepository.getById(id);
    }

    public List<Item> findItemsByCategoryId(String categoryId) {

        return itemRepository.findItemsByCategoryId(categoryId);
    }

    public List<Item> findItemsByDate(long epochStartDate, long epochEndDate) {

        Date startDate = Date.from(Instant.ofEpochSecond(epochStartDate));
        Date endDate = Date.from(Instant.ofEpochSecond(epochEndDate));

        return itemRepository.findItemsByDate(startDate, endDate);
    }

    public List<ItemDto> findItemsByBalancedIdAndDate(String balanceId, long epochStartDate, long epochEndDate) {

        Date startDate = Date.from(Instant.ofEpochSecond(epochStartDate));
        Date endDate = Date.from(Instant.ofEpochSecond(epochEndDate));

        return generateItemsDto(balanceId, startDate, endDate);
    }

    public void delete(String id) {

        itemRepository.delete(id);
    }

    private List<ItemDto> generateItemsDto(String balanceId, Date startDate, Date endDate) {

        List<Category> categories = categoryRepository.findCategoriesByBalanceId(balanceId);
        List<String> categoryIdList = categories.stream()
                                                .map(Category::getId)
                                                .collect(Collectors.toList());

        List<SubCategory> subcategories = subCategoryRepository.findByCategoryIds(categoryIdList);
        List<String> subCategoryIdList = subcategories.stream()
                                                      .map(SubCategory::getId)
                                                      .collect(Collectors.toList());

        List<String> idList =
                Stream.concat(categoryIdList.stream(), subCategoryIdList.stream()).collect(Collectors.toList());

        List<Item> itemsByCategoryIdsAndDate =
                itemRepository.findItemsByCategoryIdsAndDate(idList, startDate, endDate);

        Map<String, String> mapCategoryIdAndName =
                categories.stream().collect(Collectors.toMap(Category::getId, Category::getName));
        Map<String, String> mapSubCategoryIdAndName =
                subcategories.stream().collect(Collectors.toMap(SubCategory::getId, SubCategory::getName));

        Map<String, String> collectCategoryIdAndName =
                Stream.concat(mapCategoryIdAndName.entrySet().stream(), mapSubCategoryIdAndName.entrySet().stream())
                      .collect(
                              Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return itemsByCategoryIdsAndDate.stream()
                                        .map(item -> ItemDto.builder()
                                                            .id(item.getId())
                                                            .categoryId(item.getCategoryId())
                                                            .name(item.getName())
                                                            .comment(item.getComment())
                                                            .date(dateFormatter.format(item.getDate()))
                                                            .value(item.getValue())
                                                            .categoryName(collectCategoryIdAndName.get(item.getCategoryId()))
                                                            .build())
                                        .collect(Collectors.toList());

    }

}
