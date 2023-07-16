package com.example.accesscontrolsystem.controller;

import com.example.accesscontrolsystem.model.BuildingModel;
import com.example.accesscontrolsystem.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping("/building")
    public String getBuildingList(Model model) {
        List<BuildingModel> list = buildingService.getBuildingList();
        model.addAttribute("buildingModel", list);
        return "building/building";
    }
//
    @PostMapping("/building")
    public RedirectView postAddBuilding(BuildingModel building) {
        buildingService.addBuilding(building);
        return new RedirectView("/building");
    }
    @PatchMapping("/editBuilding/{id}")
    public RedirectView editBuilding(BuildingModel model){
        buildingService.saveEditBuilding(model);
        return new RedirectView("/building");
    }
    @PostMapping("/removeBuilding/{id}")
    public RedirectView removeBuilding(@PathVariable("id") Long id){
       buildingService.removeBuilding(id);
        return new RedirectView("/building");
    }

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
