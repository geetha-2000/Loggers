package com.logs.dao;

import java.util.List;

import com.logs.model.User;

public interface UserRepository {
    User saveUser(User user);

    User updateUser(User user);

    User getById(int id);

    void deleteById(int id);

    List<User> allUsers();
}
