package com.osama.learningspringboot.controllers;

import com.osama.learningspringboot.model.ErrorMessage;
import com.osama.learningspringboot.model.User;
import com.osama.learningspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "get")
    public List<User> fetchUsers() {
        return userService.getAllUsers();
    }

/*
    @RequestMapping(method = RequestMethod.GET, path = "{userUid}")
    public User fetchUser(@PathVariable("userUid") UUID userUid) {
        return userService.getUser(userUid).get();
    }
*/

    @RequestMapping(method = RequestMethod.GET, path = "{userUid}")
    public ResponseEntity<?> fetchUser2(@PathVariable("userUid") UUID userUid) {
        Optional<User> optionalUser = userService.getUser(userUid);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage("User " + userUid + "was not found!"));
    }

}
