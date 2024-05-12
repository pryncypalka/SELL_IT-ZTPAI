package com.example.sellit.service.user;



import com.example.sellit.model.User;
import com.example.sellit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean checkIfUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " does not exist"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " does not exist"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " does not exist"));

        Optional.ofNullable(user.getUsername()).ifPresent(existingUser::setUsername);
        Optional.ofNullable(user.getPhotoPath()).ifPresent(existingUser::setPhotoPath);
        Optional.ofNullable(user.getPassword()).ifPresent(existingUser::setPassword);

        userRepository.save(existingUser);
    }




}
