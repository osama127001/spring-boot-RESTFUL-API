package com.osama.learningspringboot.dao;

import com.osama.learningspringboot.model.Gender;
import com.osama.learningspringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() throws Exception {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    void shouldSelectAllUsers() throws Exception {
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);
        User user = users.get(0);
        assertThat(user.getAge()).isEqualTo(23);
        assertThat(user.getFirstName()).isEqualTo("Osama");
        assertThat(user.getLastName()).isEqualTo("Khan");
        assertThat(user.getGender()).isEqualTo(Gender.MALE);
        assertThat(user.getEmail()).isEqualTo("osama.khan@gmail.com");
        assertThat(user.getUserUid()).isNotNull();
    }

    @Test
    void shouldSelectUserByUserUid() throws Exception {
        UUID userUid = UUID.randomUUID();
        User user = new User(userUid, "Aamir", "Hanif", Gender.MALE, 21, "aamir.hanif@gmail.com");
        fakeDataDao.insertUser(userUid, user);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);

        Optional<User> optionalUser = fakeDataDao.selectUserByUserUid(userUid);
        assertThat(optionalUser.isPresent()).isTrue();
        assertThat(optionalUser).get().isEqualToComparingFieldByField(user);
    }

    @Test
    void shouldNotSelectUserByRandomUserUid() throws Exception {
        Optional<User> optionalUser = fakeDataDao.selectUserByUserUid(UUID.randomUUID());
        assertThat(optionalUser.isPresent()).isFalse();
    }


    @Test
    void updateUser() throws Exception {
    }

    @Test
    void deleteUserByUserUid() throws Exception {
    }

    @Test
    void insertUser() throws Exception {
    }
}