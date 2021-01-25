package com.osama.learningspringboot.service;

import com.osama.learningspringboot.dao.FakeDataDao;
import com.osama.learningspringboot.model.Gender;
import com.osama.learningspringboot.model.User;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
            return users. Kinda Forcing 'fakeDataDao.selectAllUsers()' to return our test data.
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
        UUID testUserUid = UUID.randomUUID();
        User testUser = new User(testUserUid, "Osama", "Khan",
                Gender.MALE, 23, "osama.khan@gmail.com");

        // mocking fakeDataDao.selectUserByUserUid()
        given(fakeDataDao.selectUserByUserUid(testUserUid)).willReturn(Optional.of(testUser));

        Optional<User> optionalUser = userService.getUser(testUserUid);

        assertThat(optionalUser.isPresent()).isTrue();
        User user = optionalUser.get();
        assertThat(user.getUserUid()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Osama");
        assertThat(user.getLastName()).isEqualTo("Khan");
        assertThat(user.getGender()).isEqualTo(Gender.MALE);
        assertThat(user.getAge()).isEqualTo(23);
        assertThat(user.getEmail()).isEqualTo("osama.khan@gmail.com");

    }

    @Test
    void shouldUpdateUser() throws Exception {
        UUID testUserUid = UUID.randomUUID();
        User testUser = new User(testUserUid, "Osama", "Khan",
                Gender.MALE, 23, "osama.khan@gmail.com");

        // mocking fakeDataDao.selectUserByUserUid() and fakeDataDao.updateUser()
        given(fakeDataDao.selectUserByUserUid(testUserUid)).willReturn(Optional.of(testUser));
        given(fakeDataDao.updateUser(testUser)).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        int updateResult = userService.updateUser(testUser);
        Mockito.verify(fakeDataDao).selectUserByUserUid(testUserUid);
        Mockito.verify(fakeDataDao).updateUser(captor.capture());

        User user = captor.getValue();
        assertThat(user.getUserUid()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Osama");
        assertThat(user.getLastName()).isEqualTo("Khan");
        assertThat(user.getGender()).isEqualTo(Gender.MALE);
        assertThat(user.getAge()).isEqualTo(23);
        assertThat(user.getEmail()).isEqualTo("osama.khan@gmail.com");

        assertThat(updateResult).isEqualTo(1);
    }

    @Test
    void shouldRemoveUser() throws Exception {
        UUID testUserUid = UUID.randomUUID();
        User testUser = new User(testUserUid, "Osama", "Khan",
                Gender.MALE, 23, "osama.khan@gmail.com");

        // mocking fakeDataDao.selectUserByUserUid() and fakeDataDao.updateUser()
        given(fakeDataDao.selectUserByUserUid(testUserUid)).willReturn(Optional.of(testUser));
        given(fakeDataDao.deleteUserByUserUid(testUserUid)).willReturn(1);

        int deleteResult = userService.removeUser(testUserUid);

        Mockito.verify(fakeDataDao).selectUserByUserUid(testUserUid);
        Mockito.verify(fakeDataDao).deleteUserByUserUid(testUserUid);

        assertThat(deleteResult).isEqualTo(1);
    }

    @Test
    void shouldInsertUser() throws Exception {
        User testUser = new User(null, "Osama", "Khan",
                Gender.MALE, 23, "osama.khan@gmail.com");

        given(fakeDataDao.insertUser(Mockito.any(UUID.class), Mockito.eq(testUser))).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        Mockito.verify(fakeDataDao).insertUser(Mockito.any(UUID.class), captor.capture());

        User user = captor.getValue();

        assertThat(user.getUserUid()).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Osama");
        assertThat(user.getLastName()).isEqualTo("Khan");
        assertThat(user.getGender()).isEqualTo(Gender.MALE);
        assertThat(user.getAge()).isEqualTo(23);
        assertThat(user.getEmail()).isEqualTo("osama.khan@gmail.com");

    }
}