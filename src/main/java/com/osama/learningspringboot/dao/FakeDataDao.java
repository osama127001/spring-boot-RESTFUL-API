package com.osama.learningspringboot.dao;

import com.osama.learningspringboot.model.Gender;
import com.osama.learningspringboot.model.User;

import java.util.*;

public class FakeDataDao implements UserDao {

    private static Map<UUID, User> database;

    static {
        database = new HashMap<>();
        UUID osamaUserUid = UUID.randomUUID();
        database.put(osamaUserUid, new User(osamaUserUid, "Osama", "Khan",
                Gender.MALE, 23, "osama.khan@gmail.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public User getUser(UUID userUid) {
        return database.get(userUid);
    }

    @Override
    public int updateUser(User user) {
        database.put(user.getUserUid(), user);
        return 1;
    }

    @Override
    public int removeUser(UUID userUid) {
        database.remove(userUid);
        return 1;
    }

    @Override
    public int insertUser(UUID userUid, User user) {
        database.put(userUid, user);
        return 1;
    }
}
