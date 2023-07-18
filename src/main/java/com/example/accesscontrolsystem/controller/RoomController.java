package com.example.accesscontrolsystem.controller;

import com.example.accesscontrolsystem.model.BuildingModel;
import com.example.accesscontrolsystem.model.RoomModel;
import com.example.accesscontrolsystem.service.BuildingService;
import com.example.accesscontrolsystem.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class RoomController {
   private final RoomService roomService;
    private final BuildingService buildingService;
    @GetMapping("/rooms")
    public String getBuildingList(Model model) {
        List<RoomModel> list = roomService.getRoomList();
        model.addAttribute("rooms", list);
        return "room/room";
    }
    @GetMapping("/addRooms")
    public String getAddRoom(Model model) {
        List<BuildingModel> buildingList = buildingService.getBuildingList();
        model.addAttribute("addedRoom", new RoomModel());
        model.addAttribute("buildingsList",buildingList );
        return "room/add-room";
    }

    @PostMapping("/addRoom")
    public String addRoom(RoomModel newRoom, BindingResult result) {
        if (result.hasErrors()) {
            return "room/add-room";
        }
        roomService.addRoom(newRoom);
        return "redirect:/rooms";
    }
}
