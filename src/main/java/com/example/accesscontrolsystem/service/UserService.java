package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.model.UserModel;
import com.example.accesscontrolsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    public String getUsersCount(List<UserModel> user) {
        return "Total number of users: " + user.size();
    }

    public void sortUsers(List<UserModel> users, String sortBy) {
        if (sortBy != null) {
            switch (sortBy) {
                case "id":
                    users.sort(Comparator.comparing(UserModel::getId));
                    break;
                case "name":
                    users.sort(Comparator.comparing(u -> u.getName().toLowerCase()));
                    break;
                case "surname":
                    users.sort(Comparator.comparing(u -> u.getSurname().toLowerCase()));
                    break;
//                case "buildingModel":
//                    users.sort(Comparator.comparing(UserModel::getBuildingModel));
//                    break;
                case "department":
                    users.sort(Comparator.comparing(u -> u.getDepartment().getDisplayText().toLowerCase()));
                    break;
                default:
                    // Obsłuż nieznany parametr sortowania
                    break;
            }
        }
    }
}
