package com.example.sellit.service.user;



import com.example.sellit.dto.PasswordChangeRequest;
import com.example.sellit.model.User;
import com.example.sellit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public Long getUserIdByEmail(String email) {
        return userRepository.GetUserIdByEmail(email);
    }

    @Override
    public void updatePhotoPath(Long userId, String photoPath) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
        user.setPhotoPath(photoPath);
        userRepository.save(user);
    }

    @Override
    public Integer getUserFreeOffers(String email) {
        return userRepository.findUserFreeOffersByEmail(email);
    }

    @Override
    public void updateUserFreeOffers(Long userId, Integer i){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
        user.setFreeOffers(i);
        userRepository.save(user);
    }
    @Override
    public void changePassword(User user, PasswordChangeRequest request) {
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
    @Override
    public Integer getFreeOffers(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getFreeOffers();
    }
}
