package com.osama.learningspringboot.model;

import java.util.UUID;

public class User {

    private final UUID userUid;
    private final String firsName;
    private final String lastName;
    private final Gender gender;
    private final short age;
    private final String email;

    public User(UUID userUid, String firsName, String lastName, Gender gender, short age, String email) {
        this.userUid = userUid;
        this.firsName = firsName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public UUID getUserUid() {
        return userUid;
    }

    public String getFirsName() {
        return firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public short getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userUid=" + userUid +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
