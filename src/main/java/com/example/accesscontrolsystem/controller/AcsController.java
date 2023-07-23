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

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AcsController {
    private final BuildingService buildingService;
    private final UserService userService;
    private final RoomService roomService;
    private final AcsService acsService;

    @GetMapping("/acs")
    public String getBuildingList(Model model) { //, @RequestParam Boolean res -> trzeba było usunąć bo nie działało get maping na acs tylko za każdym razem trzeba było podać parametr
        List<BuildingModel> listBuildings = buildingService.getBuildingList();
        List<RoomModel> listRoom = roomService.getRoomList();
        List<UserModel> list = userService.getUserList();
        model.addAttribute("buildings", listBuildings);
        model.addAttribute("rooms", listRoom);
        model.addAttribute("users", list);


        return "acs/acs";
    }

    @PostMapping("/checkBuilding")
    public String postCheckBuilding(@RequestParam Long userId, @RequestParam Long roomId, Model model) {
        List<RoomModel> listRoom = roomService.getRoomList();
        List<UserModel> list = userService.getUserList();
        model.addAttribute("rooms", listRoom);
        model.addAttribute("users", list);
        Boolean resBuilding = acsService.checkIfUserHasAccessToBuilding(userId, roomId);
        model.addAttribute("resBuilding", resBuilding);
        return "acs/acs";
    }

    @PostMapping("/checkPosition")
    public String postCheckPosition(@RequestParam Long userId, Model model) {
        List<UserModel> list = userService.getUserList();
        model.addAttribute("users", list);
        Boolean resPosition = acsService.checkPositionUser(userId);
        model.addAttribute("resPosition", resPosition);
        return "acs/acs";
    }
//    @PostMapping("/acsCheckBuilding")
//    public String checkBuildings(@RequestParam Long user, @RequestParam Long room, Model model){ // RedirectAttributes redirectAttributes
//        Boolean res = acsService.checkIfUserHasAccessToBuilding(1L, 1L);
//        model.addAttribute("res", res);
//        return "acs/acs";
//    }


}
//    @GetMapping("/acsCheckBuilding")
//    public String getCheckBuilding(Model model) {
//        List<RoomModel> listRoom = roomService.getRoomList();
//        List<UserModel> list = userService.getUserList();
//        model.addAttribute("rooms", listRoom);
//        model.addAttribute("users", list);
//
//        return "acs/check-building";
//    }
//    @PostMapping("/acsCheckBuilding")
//    public String checkBuildings(@RequestParam Long user, @RequestParam Long room, Model model){ // RedirectAttributes redirectAttributes
//        Boolean res = acsService.checkIfUserHasAccessToBuilding(1L, 1L);
//        model.addAttribute("res", res);
//        return "acs/acs";
//    }