package com.esprit.usermicroservice.servicesImpl;

import com.esprit.usermicroservice.entities.User;
import com.esprit.usermicroservice.repositories.UserRepository;
import com.esprit.usermicroservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String ERROR_NULL_ID = "Posted ID is NULL";
    private static final String ERROR_NON_PRESENT_ID = "Cannot find a user with id : %s";
    private static final String ERROR_UPDATE = "Error occurred while updating";

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(String userId) {
        User user = null;
        if (userId != null) {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            } else {
                LOG.info(String.format(ERROR_NON_PRESENT_ID, userId));
            }
        } else {
            LOG.error(ERROR_NULL_ID);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = null;
        if (user != null) {
            updatedUser = userRepository.save(user);
        } else {
            LOG.error(ERROR_UPDATE);
        }
        return updatedUser;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void printAllUsers() {
        System.out.println("Current Date and Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println("List of Users: ");

        List<User> users = userRepository.findAll();

        for (User user : users) {
            System.out.println("---------------------------------------");
            System.out.println("User ID: " + user.getUserID());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Email address: " + user.getEmail());
            System.out.println("Date of brith: " + user.getDateOfBirth());
            System.out.println("Role: " + user.getRole());
        }

    }
}
