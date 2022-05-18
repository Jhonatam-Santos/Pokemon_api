package com.example.crudsimpleapi.controller;

import com.example.crudsimpleapi.entity.User;
import com.example.crudsimpleapi.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/listAll")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users =  new ArrayList<User>();
        users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id) {
        Optional<User> user;
        try {
            user = userRepository.findById(id);
            return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
        }
        catch (NoSuchElementException err) {
            return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Optional<User>> removeUserById(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<Optional<User>>(HttpStatus.OK);
        }
        catch (NoSuchElementException err) {
            return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setPassword(newUser.getPassword());
            User userUpdate = userRepository.save(user);
            return ResponseEntity.ok().body(userUpdate);
        }).orElse(ResponseEntity.notFound().build());
    }
}
