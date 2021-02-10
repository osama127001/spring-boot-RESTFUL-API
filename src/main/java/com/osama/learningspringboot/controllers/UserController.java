package com.osama.learningspringboot.controllers;

import com.osama.learningspringboot.model.ErrorMessage;
import com.osama.learningspringboot.model.User;
import com.osama.learningspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private UserService userService;


    /*
    * CONSTRUCTOR: Injecting User service. */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /*
    * GET: All users from database. */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "get",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<User> fetchUsers(@QueryParam("gender") String gender) {
        return userService.getAllUsers(Optional.ofNullable(gender));
    }


    /*
    * GET: User by ID using path variable and ResponseEntity and ResponseCode. */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "{userUid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> fetchUser(@PathVariable("userUid") UUID userUid) {
        Optional<User> optionalUser = userService.getUser(userUid);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage("User " + userUid + "was not found!"));

        // The below method also works good if the response type is User.
        // return userService.getUser(userUid).orElseThrow(() -> new NotFoundException("user " + userUid + "Not found!"));
    }


    /*
    * POST: Saving a user in the database. */
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> insertNewUser(@RequestBody @Valid User user) {
        int result = userService.insertUser(user);
        return getIntegerResponseEntity(result);
    }


    /*
    * PUT: Updates the user object. */
    @RequestMapping(
            method = RequestMethod.PUT,
            path = "{userUid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> updateUser(@RequestBody @Valid User user, @PathVariable("userUid") UUID userUid) {
        int result = userService.updateUser(user, userUid);
        return getIntegerResponseEntity(result);
    }

    /*
    * DELETE: Delete a user using userUid */
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{userUid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> deleteUser(@PathVariable("userUid") UUID userUid) {
        int result = userService.removeUser(userUid);
        return getIntegerResponseEntity(result);
    }


    /*
    * Returns: Response of the request
    * Used In: PUT, POST */
    private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
        if (result == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
