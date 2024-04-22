package com.esprit.usermicroservice.services;

import com.esprit.usermicroservice.dtos.EventUserDto;
import com.esprit.usermicroservice.entities.User;

import java.util.List;

public interface UserService {
    /**
     * Fetch the List of users.
     *
     * @return list of User.
     */
    List<User> getAllUsers();

    /**
     * Fetch User by a given Id.
     *
     * @param userId
     * @return User
     */
    EventUserDto findUserById(String userId);

    /**
     * Updates a given user fields.
     *
     * @param user
     * @return updated university object.
     */
    User updateUser(User user);

    /**
     * Add a user given user fields
     * @param user
     * @return user object
     */
    User addUser(User user);

    /**
     * Remove a user given userId
     * @param userId
     */
    void deleteUserById(String userId);

    /**
     * print the list of users
     */
    void printAllUsers();
}
