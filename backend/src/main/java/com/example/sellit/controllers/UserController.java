package com.example.sellit.controllers;

import com.example.sellit.dto.UsersDto;
import com.example.sellit.models.Users;
import com.example.sellit.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.sellit.mapper.UsersMapper.toDto;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addUser")
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        if (usersService.checkIfUserExists(user.getEmail())) {
            return new ResponseEntity<>("User with email " + user.getEmail() + " already exists", HttpStatus.CONFLICT);
        }
        usersService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UsersDto> getUser(@PathVariable Long userId) {
        Users user = usersService.getUser(userId);

        return new ResponseEntity<>(toDto(user), HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UsersDto> getUserByEmail(@PathVariable String email) {
        Users user = usersService.getUserByEmail(email);

        return new ResponseEntity<>(toDto(user), HttpStatus.OK);
    }

    @PatchMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody Users user) {
        Users existingUser = usersService.getUser(userId);
        if (existingUser != null) {
            usersService.updateUser(userId, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with id " + userId + " does not exist", HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Users user = usersService.getUser(userId);
        if (user != null) {
            usersService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User with id " + userId + " does not exist", HttpStatus.NOT_FOUND);
        }
    }
}