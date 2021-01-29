package com.osama.learningspringboot.service;

import com.osama.learningspringboot.dao.UserDao;
import com.osama.learningspringboot.model.Gender;
import com.osama.learningspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers(Optional<String> gender) {
        List<User> users = userDao.selectAllUsers();
        if (!gender.isPresent()) {
            return users;
        }
        try {
            Gender theGender = Gender.valueOf(gender.get().toUpperCase());
            return users.stream()
                    .filter(user -> user.getGender().equals(theGender))
                    .collect(Collectors.toList());
        } catch (Exception exp) {
            throw new IllegalStateException("Invalid Gender" + exp);
        }
    }

    public Optional<User> getUser(UUID userUid) {
        return userDao.selectUserByUserUid(userUid);
    }

    public int updateUser(User user) {
        Optional<User> optionalUser = getUser(user.getUserUid());
        if (optionalUser.isPresent()) {
            return userDao.updateUser(user);
        }
        return -1;
    }

    public int removeUser(UUID userUid) {
        Optional<User> optionalUser = getUser(userUid);
        if (optionalUser.isPresent()) {
            return userDao.deleteUserByUserUid(userUid);
        }
        return -1;
    }

    public int insertUser(User user) {
        UUID userUid = UUID.randomUUID();
        // validateUser(user);
        return userDao.insertUser(userUid, User.newUser(userUid, user));
    }

    private void validateUser(User user) {
        Objects.requireNonNull(user.getFirstName(), "first name required!");
        Objects.requireNonNull(user.getLastName(), "last name required!");
        Objects.requireNonNull(user.getAge(), "age required!");
        Objects.requireNonNull(user.getEmail(), "email required!");
    }


}
