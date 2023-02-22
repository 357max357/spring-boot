package ru.javamentor.springboot.dao;

import ru.javamentor.springboot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getById(long id);
}