package com.home.dandrusiv.accounting.repositories;

import com.home.dandrusiv.accounting.models.User;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    MongoOperations operations;

    private String USER_DOCUMENT = "user";


    @Override
    public User create(User user) {
        return operations.insert(user, USER_DOCUMENT);
    }

    @Override
    public User update(final User user) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));

        final UpdateResult updateResult = operations.updateFirst(query, updateUser(user), USER_DOCUMENT);

        if(updateResult.wasAcknowledged()) {
            return operations.findOne(query, User.class);
        } else {
            throw new RuntimeException("Error during updating user by id:" + user.getId());
        }
    }

    @Override
    public User getById(String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        return operations.findOne(query, User.class);
    }

    @Override
    public User getByEmail(String email) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        return operations.findOne(query, User.class);
    }

    @Override
    public List<User> findByFirstName(String firstName) {

        Aggregation agg = newAggregation(match(Criteria.where("firstName").is(firstName)));

        return operations.aggregate(agg, "user", User.class).getMappedResults();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        Aggregation agg = newAggregation(match(Criteria.where("lastName").is(lastName)));

        return operations.aggregate(agg, "user", User.class).getMappedResults();
    }

    @Override
    public List<User> findByRole(String role) {
        Aggregation agg = newAggregation(match(Criteria.where("role").is(role)));

        return operations.aggregate(agg, "user", User.class).getMappedResults();
    }

    @Override
    public void delete(String userId) {
        operations.remove(Criteria.where("id").is(userId), USER_DOCUMENT);
    }

    protected Update updateUser(User user) {
        Update update = new Update();

        update.set("email", user.getEmail());
        update.set("firstName", user.getFirstName());
        update.set("lastName", user.getLastName());
        update.set("password", user.getPassword());
        update.set("role", user.getRole());

        return update;
    }
}
