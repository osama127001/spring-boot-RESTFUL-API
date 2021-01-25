package com.osama.learningspringboot.dao;

import com.osama.learningspringboot.model.Gender;
import com.osama.learningspringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void selectUserByUserUid() throws Exception {

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