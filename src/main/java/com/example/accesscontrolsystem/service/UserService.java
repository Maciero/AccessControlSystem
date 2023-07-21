package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.model.UserModel;
import com.example.accesscontrolsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<UserModel> getUserList() {
        return (List<UserModel>) userRepository.findAll();
    }

    public void addUser(UserModel user) {
        userRepository.save(user);
    }


    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void saveEditUser(UserModel user) {
        userRepository.save(user);
    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    public String getUsersCount(List<UserModel> list) {
        return "Total number of users: " + list.size();
    }
}
