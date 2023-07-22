package com.example.accesscontrolsystem.controller;

import com.example.accesscontrolsystem.model.BuildingModel;
import com.example.accesscontrolsystem.model.RoomModel;
import com.example.accesscontrolsystem.model.UserModel;
import com.example.accesscontrolsystem.service.AcsService;
import com.example.accesscontrolsystem.service.BuildingService;
import com.example.accesscontrolsystem.service.RoomService;
import com.example.accesscontrolsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AcsController {
    private final BuildingService buildingService;
    private final UserService userService;
    private final RoomService roomService;
    private final AcsService acsService;

    @GetMapping("/acs")
    public String getBuildingList(Model model, @RequestParam Boolean res) {
        List<BuildingModel> listBuildings = buildingService.getBuildingList();
        List<RoomModel> listRoom = roomService.getRoomList();
        List<UserModel> list = userService.getUserList();
        model.addAttribute("buildings", listBuildings);
        model.addAttribute("rooms", listRoom);
        model.addAttribute("users", list);
        model.addAttribute("res", res);

        return "acs/acs";
    }

    @PostMapping("/acsCheckBuilding")
    public String checkBuildings(Model model, RedirectAttributes redirectAttributes){
        Boolean res = acsService.checkIfUserHasAccessToBuilding(1L, 2L);

        redirectAttributes.addAttribute("res", res);
        return "redirect:/acs";
    }


}
