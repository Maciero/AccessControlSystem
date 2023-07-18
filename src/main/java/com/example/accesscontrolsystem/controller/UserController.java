package com.example.accesscontrolsystem.controller;

import com.example.accesscontrolsystem.model.BuildingModel;
import com.example.accesscontrolsystem.model.RoomModel;
import com.example.accesscontrolsystem.model.UserModel;
import com.example.accesscontrolsystem.repository.UserRepository;
import com.example.accesscontrolsystem.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
//    private final BuildingService buildingService;

    @GetMapping("/")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    @GetMapping("/signup")
    public String showSignUpForm(UserModel user,Model model) {
//        List<BuildingModel> buildingList = buildingService.getBuildingList();
//        model.addAttribute("buildingsList",buildingList );
        return "add-user";
    }


    @PostMapping("/adduser")
    public String addUser( UserModel user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,  UserModel user,
                              Model model) {
        model.addAttribute("user", user);
        userRepository.save(user);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/";
    }
}