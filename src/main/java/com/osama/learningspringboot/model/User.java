package com.osama.learningspringboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {

    private final UUID userUid;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final Number age;
    private final String email;


    public User(
            @JsonProperty("userUid") UUID userUid,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("age") Number age,
            @JsonProperty("email") String email
    ) {
        this.userUid = userUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public static User newUser(UUID userUid, User user) {
        return new User(userUid, user.getFirstName(), user.getLastName(), user.getGender(), user.getAge(), user.getEmail());
    }


    @JsonProperty("id")
    public UUID getUserUid() {
        return userUid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Number getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userUid=" + userUid +
                ", firsName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
