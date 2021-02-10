package com.osama.learningspringboot.dao;

import com.osama.learningspringboot.model.Gender;
import com.osama.learningspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

@Repository
public class FakeDataDao implements UserDao {

    private Map<UUID, User> database;

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public FakeDataDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        database = new HashMap<>();
        UUID osamaUserUid = UUID.randomUUID();
        database.put(osamaUserUid, new User(osamaUserUid, "Osama", "Khan",
                Gender.MALE, 23, "osama.khan@gmail.com"));
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<User> selectAllUsers() {
        // return new ArrayList<>(database.values());
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<User>();
        try {
            users = jdbcTemplate.query(
                    sql,
                    (resultSet, rowNum) -> new User(
                            UUID.fromString(resultSet.getString("id")),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            Gender.valueOf(resultSet.getString("gender")),
                            resultSet.getInt("age"),
                            resultSet.getString("email")
                    ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }


    @Override
    public Optional<User> selectUserByUserUid(UUID userUid) {
        // return Optional.ofNullable(database.get(userUid));
        Optional<User> user = Optional.empty();
        try {
            user = Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT * FROM user WHERE user.id = ?",
                    new Object[]{userUid.toString()},
                    (resultSet, rowNum) -> new User(
                            UUID.fromString(resultSet.getString("id")),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            Gender.valueOf(resultSet.getString("gender")),
                            resultSet.getInt("age"),
                            resultSet.getString("email")
                    ))
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }


    @Override
    public int updateUser(User user, UUID userUid) {
        database.put(user.getUserUid(), user);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("lastName", user.getLastName());
        mapSqlParameterSource.addValue("userId", userUid.toString());
        try {
            namedParameterJdbcTemplate.update(
                    "UPDATE user SET user.last_name = :lastName WHERE user.id = :userId",
                    mapSqlParameterSource);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }


    @Override
    public int deleteUserByUserUid(UUID userUid) {
        database.remove(userUid);
        try {
            namedParameterJdbcTemplate.update(
                    "DELETE FROM user WHERE user.id = :userId",
                    new MapSqlParameterSource("userId", userUid.toString())
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }


    @Override
    public int insertUser(UUID userUid, User user) {
        database.put(userUid, user);
        String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                        sql,
                        userUid.toString(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getGender().toString(),
                        user.getAge(),
                        user.getEmail()
                    );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 1;
    }
}
