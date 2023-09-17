package com.example.java_api;

import com.example.java_api.models.User;
import com.example.java_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MyController {

    private UserService userService;

    public MyController(UserService userSvc){
        this.userService = userSvc;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
    @GetMapping("/get")
    public String get() {
        return "Get";
    }

    @GetMapping("/update")
    public String update() {
        return "Update";
    }

    @GetMapping("/delete")
    public String delete() {
        return "Delete";
    }

    @GetMapping("/put")
    public String put() {return "Put"; }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id){
        Optional user = userService.getUser(id);
        if(user.isPresent()){
            return (User) user.get();
        }
        return null;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        Optional<User> existingUser = userService.getUser(id);
        if (existingUser.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestParam Integer id, @RequestBody User updatedUser) throws Exception {
        Optional<User> existingUser = userService.getUser(id);

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setAge(updatedUser.getAge());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setName(updatedUser.getName());

            User updatedUserEntity = userService.updateUser(userToUpdate);

            return ResponseEntity.ok(updatedUserEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
