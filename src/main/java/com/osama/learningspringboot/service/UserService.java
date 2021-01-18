package com.osama.learningspringboot.service;

import com.osama.learningspringboot.dao.UserDao;
import com.osama.learningspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public User getUser(UUID userUid) {
        return null;
    }

    public int updateUser(User user) {
        return 1;
    }

    public int removeUser(UUID userUid) {
        return 1;
    }

    public int insertUser(UUID userUid, User user) {
        return 1;
    }


}
