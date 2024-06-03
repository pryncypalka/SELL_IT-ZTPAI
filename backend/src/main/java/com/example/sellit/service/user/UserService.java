package com.example.sellit.service.user;
import com.example.sellit.dto.PasswordChangeRequest;
import com.example.sellit.model.User;

import java.util.List;

public interface UserService {
    boolean checkIfUserExists(String email);
    void createUser(User user);
    User getUser(Long userId);
    User getUserByEmail(String email);
    void deleteUser(Long userId);

    void updateUser(Long userId, User user);

    Long getUserIdByEmail(String name);

    void updatePhotoPath(Long userId, String photoPath);

    Integer getUserFreeOffers(String email);


    void updateUserFreeOffers(Long userId, Integer i);

    void changePassword(User user, PasswordChangeRequest request);

    Integer getFreeOffers(Long userId);

    Boolean isAdmin(Long userId);

    List<User> getAllUsers();
}
