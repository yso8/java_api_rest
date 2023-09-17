package com.example.java_api.service;

import com.example.java_api.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList;
    public UserService() {
        userList = new ArrayList<>();

        User user1 = new User(1, "Ida", 32, "ida@gmail.com");
        User user2 = new User(2, "Hans", 26, "hans@gmail.com");
        User user3 = new User(3, "Lars", 45, "lars@gmail.com");
        User user4 = new User(4, "Ben", 32, "ben@gmail.com");
        User user5 = new User(5, "Eva", 59, "eva@gmail.com");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
    public Optional<User> getUser(Integer id) {
        Optional optional = Optional.empty();
        for (User user: userList) {
            if(id == user.getId()){
                optional = Optional.of(user);
            }
        }
        return optional;
    }

    public User createUser(User user) {
        userList.add(user);
        return user;
    }

    public void deleteUser(Integer id) {
        for (User user : userList) {
            if (user.getId() == id) {
                userList.remove(user);
                return;
            }
        }
    }

    public User updateUser(User updatedUser) throws Exception {
        User existingUser = null;

        for (User user : userList) {
            if (user.getId() == updatedUser.getId()) {
                existingUser = updatedUser;
            }
        }

        if (existingUser != null) {
            userList.remove(existingUser);
            userList.add(updatedUser);
        } else {
            throw new Exception("Utilisateur non trouvé avec l'ID : " + updatedUser.getId());
        }
        return updatedUser;
    }
}

