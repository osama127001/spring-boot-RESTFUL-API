package com.osama.learningspringboot.model;

import java.util.UUID;

public class User {

    private UUID userUid;
    private String firsName;
    private String lastName;
    private Gender gender;
    private Number age;
    private String email;

    public User() {
    }

    public User(UUID userUid, String firsName, String lastName, Gender gender, Number age, String email) {
        this.userUid = userUid;
        this.firsName = firsName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public void setUserUid(UUID userUid) {
        this.userUid = userUid;
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
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
