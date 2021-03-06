package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoOperations operations;

    private String USER_DOCUMENT = "user";

    @Override
    public User create(final User user) {
        return operations.insert(user, USER_DOCUMENT);
    }

    @Override
    public User update(final User user) {
        return operations.save(user);
    }

    @Override
    public User getById(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, User.class);
    }

    @Override
    public User getByEmail(final String email) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        return operations.findOne(query, User.class);
    }

    @Override
    public List<User> findByFirstName(final String firstName) {

        final Aggregation agg = newAggregation(match(Criteria.where("firstName").is(firstName)));

        return operations.aggregate(agg, USER_DOCUMENT, User.class).getMappedResults();
    }

    @Override
    public List<User> findByLastName(final String lastName) {
        final Aggregation agg = newAggregation(match(Criteria.where("lastName").is(lastName)));

        return operations.aggregate(agg, USER_DOCUMENT, User.class).getMappedResults();
    }

    @Override
    public List<User> findByRole(final String role) {
        final Aggregation agg = newAggregation(match(Criteria.where("role").is(role)));

        return operations.aggregate(agg, USER_DOCUMENT, User.class).getMappedResults();
    }

    @Override public List<User> findAll() {
        return operations.findAll(User.class);
    }

    @Override
    public void delete(final String userId) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));

        operations.findAndRemove(query, User.class);
    }

    @Override
    public void deleteAll() {
        operations.dropCollection(USER_DOCUMENT);
    }

}
