package com.example.accesscontrolsystem.controller;

import com.example.accesscontrolsystem.model.UserModel;
import com.example.accesscontrolsystem.repository.UserRepository;
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
    @GetMapping("/")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    @GetMapping("/signup")
    public String showSignUpForm(UserModel user) {
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
        return "redirect:/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/index";
    }

}


//    @GetMapping("/education")
//    public String getEducationList(Model model) {
//        List<EducationModel> list = educationService.getEducationList();
//        model.addAttribute("educationModel", list);
//        return "education/education";
//    }
//
//    @PostMapping("/education")
//    public RedirectView postAddEducation(EducationModel education) {
//        educationService.addEducation(education);
//        return new RedirectView("/education");
//    }
//
//    @GetMapping("/education/{id}")
//    public String getTask(@PathVariable("id") Long id, Model model) {
//
//        EducationModel task = educationService.getEducationById(id);
//        model.addAttribute("student");
//        model.addAttribute("task", task);
//        return "education/education";
//    }
//
//    @PostMapping("/removeEducation/{id}")
//    public RedirectView removeEducation(@PathVariable("id") Long id) {
//        educationService.removeEducation(id);
//        return new RedirectView("/education");
//    }
//
//    @PostMapping("/editEducation/{id}")
//    public RedirectView patchEdit(@PathVariable("id") Long id, EducationModel educationModel) {
//        educationService.saveEditTask(educationModel);
//        return new RedirectView("/education");
//    }