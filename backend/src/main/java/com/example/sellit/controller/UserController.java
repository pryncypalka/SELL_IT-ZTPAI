package com.example.sellit.controller;

import com.example.sellit.dto.UserDto;
import com.example.sellit.model.User;
import com.example.sellit.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.sellit.mapper.UserMapper.toDto;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.checkIfUserExists(user.getEmail())) {
            return new ResponseEntity<>("User with email " + user.getEmail() + " already exists", HttpStatus.CONFLICT);
        }
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);

        return new ResponseEntity<>(toDto(user), HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);

        return new ResponseEntity<>(toDto(user), HttpStatus.OK);
    }

    @PatchMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {
        User existingUser = userService.getUser(userId);
        if (existingUser != null) {
            userService.updateUser(userId, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with id " + userId + " does not exist", HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User with id " + userId + " does not exist", HttpStatus.NOT_FOUND);
        }
    }
}