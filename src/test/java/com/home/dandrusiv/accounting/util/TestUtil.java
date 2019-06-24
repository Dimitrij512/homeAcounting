package com.home.dandrusiv.accounting.util;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.home.dandrusiv.accounting.models.User;

public class TestUtil {

    public static User prepareUser(String email, String name, String lastName) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setPassword("************");

        return user;
    }

    public static void printJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(object));
    }

}
