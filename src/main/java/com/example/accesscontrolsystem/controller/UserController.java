package com.example.accesscontrolsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

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
}
