package com.example.sellit.service.users;



import com.example.sellit.models.Users;
import com.example.sellit.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsersServiceImp implements UsersService {

    private final UsersRepository usersRepository;


    @Autowired
    public UsersServiceImp(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public boolean checkIfUserExists(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }
    @Override
    public void createUser(Users user) {
        usersRepository.save(user);
    }

    @Override
    public Users getUser(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " does not exist"));
    }

    @Override
    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " does not exist"));
    }

    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public void updateUser(Long userId, Users user) {
        Users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " does not exist"));

        Optional.ofNullable(user.getUsername()).ifPresent(existingUser::setUsername);
        Optional.ofNullable(user.getPhotoPath()).ifPresent(existingUser::setPhotoPath);
        Optional.ofNullable(user.getPassword()).ifPresent(existingUser::setPassword);

        usersRepository.save(existingUser);
    }




}
