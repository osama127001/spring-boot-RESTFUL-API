package com.osama.learningspringboot.service;

import com.osama.learningspringboot.dao.FakeDataDao;
import com.osama.learningspringboot.model.Gender;
import com.osama.learningspringboot.model.User;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

class UserServiceTest {

    /*
        Mocking this because it is already tested
    */
    @Mock
    private FakeDataDao fakeDataDao;

    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(fakeDataDao);
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        UUID newUserUid = UUID.randomUUID();
        User newUser = new User(newUserUid, "Osama", "Khan",
                Gender.MALE, 23, "osama.khan@gmail.com");

        ArrayList<User> users = new ArrayList<User>();
        users.add(newUser);

        /*
            This given() statement shows that the 'fakeDataDao.selectAllUsers()' inside the fakeDataDao class will
            return users.
        */
        given(fakeDataDao.selectAllUsers()).willReturn(users);

        List<User> allUsers = userService.getAllUsers(Optional.empty());

        assertThat(allUsers).hasSize(1);

        User user = allUsers.get(0);
        assertThat(user.getUserUid()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Osama");
        assertThat(user.getLastName()).isEqualTo("Khan");
        assertThat(user.getGender()).isEqualTo(Gender.MALE);
        assertThat(user.getAge()).isEqualTo(23);
        assertThat(user.getEmail()).isEqualTo("osama.khan@gmail.com");

    }

    @Test
    void shouldGetUser() throws Exception {
    }

    @Test
    void shouldUpdateUser() throws Exception {
    }

    @Test
    void shouldRemoveUser() throws Exception {
    }

    @Test
    void shouldInsertUser() throws Exception {
    }
}