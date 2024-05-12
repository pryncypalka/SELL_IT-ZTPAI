package com.example.sellit.service.users;


import com.example.sellit.models.Users;



public interface UsersService {
    boolean checkIfUserExists(String email);
    void createUser(Users user);

    Users getUser(Long userId);
    Users getUserByEmail(String email);
    void deleteUser(Long userId);

    void updateUser(Long userId, Users user);

}
