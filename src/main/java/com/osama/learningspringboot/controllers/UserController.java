package com.osama.learningspringboot.controllers;

import com.osama.learningspringboot.model.ErrorMessage;
import com.osama.learningspringboot.model.User;
import com.osama.learningspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private UserService userService;


    /*
         CONSTRUCTOR: Injecting User service.
    */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /*
         GET: All users from database.
    */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "get",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<User> fetchUsers(@QueryParam("gender") String gender) {
        return userService.getAllUsers(Optional.ofNullable(gender));
    }


    /*
         Returns: Response of the request
         Used In: PUT, POST
    */
    private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
        if (result == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    /*
         GET: User by ID using path variable and ResponseEntity and ResponseCode.
    */
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
    }


    /*
         POST: Saving a user in the database.
    */
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> insertNewUser(@RequestBody User user) {
        int result = userService.insertUser(user);
        return getIntegerResponseEntity(result);
    }


    /*
         PUT: Updates the user object.
    */
    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> updateUser(@RequestBody User user) {
        int result = userService.updateUser(user);
        return getIntegerResponseEntity(result);
    }

    /*
        DELETE: Delete a user using userUid
    */
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{userUid}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> deleteUser(@PathVariable("userUid") UUID userUid) {
        int result = userService.removeUser(userUid);
        return getIntegerResponseEntity(result);
    }

}
