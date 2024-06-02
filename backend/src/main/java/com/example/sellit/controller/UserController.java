package com.example.sellit.controller;

import com.example.sellit.dto.PasswordChangeRequest;
import com.example.sellit.dto.UserDto;
import com.example.sellit.model.User;
import com.example.sellit.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    @GetMapping("/get")
    public ResponseEntity<UserDto> getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        User user = userService.getUser(userId);

        return new ResponseEntity<>(toDto(user), HttpStatus.OK);
    }

    @GetMapping("/get-user-by-email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {

        User user = userService.getUserByEmail(email);

        return new ResponseEntity<>(toDto(user), HttpStatus.OK);
    }

    @PatchMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        User existingUser = userService.getUser(userId);
        if (existingUser != null) {
            userService.updateUser(userId, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with id " + userId + " does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        User user = userService.getUser(userId);
        if (user != null) {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User with id " + userId + " does not exist", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(path = "/update-image")
    public ResponseEntity<Void> updateImageUrl(@RequestParam("file") MultipartFile file) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = auth.getName() + "_" + timestamp + "_" + file.getOriginalFilename();
        Path path = Paths.get("src/main/java/com/example/sellit/images/avatars/" + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userService.updatePhotoPath(userId, path.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<?> changePassword(
            @RequestBody PasswordChangeRequest request
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        try {
            userService.changePassword(userService.getUser(userId), request);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/images")
    public ResponseEntity<Resource> getImage(@RequestParam String path) {
        try {
            Path file = Paths.get(path);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/freeOffers")
    public ResponseEntity<Integer> getFreeOffers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        Integer freeOffers = userService.getFreeOffers(userId);
        return ResponseEntity.ok(freeOffers);
    }
}