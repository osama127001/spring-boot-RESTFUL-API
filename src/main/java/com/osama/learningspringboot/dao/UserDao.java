package com.osama.learningspringboot.dao;

import com.osama.learningspringboot.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    List<User> getAllUsers();

    User getUser(UUID userUid);

    int updateUser(User user);

    int removeUser(UUID userUid);

    int insertUser(User user);

}
