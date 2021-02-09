package com.osama.learningspringboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    @Id
    private final UUID userUid;

    @Column(name = "first_name")
    @NotNull(message = "FirstName cannot be null")
    private final String firstName;

    @Column(name = "last_name")
    @NotNull
    private final String lastName;

    @Column(name = "gender")
    @NotNull
    private final Gender gender;

    @Column(name = "age")
    @NotNull
    @Max(value = 110)
    @Min(value = 0)
    private final Integer age;

    @Column(name = "email")
    @NotNull
    @Email
    private final String email;


    public User(
            @JsonProperty("userUid") UUID userUid,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("age") Integer age,
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

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public LocalDate getDateOfBirth() {
        return LocalDate.now().minusYears(this.age);
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

    public Integer getAge() {
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
