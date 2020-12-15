package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.Income;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends CrudRepository<Income, String> {

/*    void addCategory(final Category category);
    void removeCategory(final String id);*/
}
