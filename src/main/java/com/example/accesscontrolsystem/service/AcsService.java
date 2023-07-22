package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.model.BuildingModel;
import com.example.accesscontrolsystem.model.RoomModel;
import com.example.accesscontrolsystem.model.UserModel;
import com.example.accesscontrolsystem.repository.AcsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AcsService {
    private final BuildingService buildingService;
    private final UserService userService;
    private final RoomService roomService;
    private final AcsRepository acsRepository;


    public boolean checkIfUserHasAccessToBuilding(Long userId, Long roomId) {
        UserModel user = userService.getUserById(userId);
        RoomModel room = roomService.getRoomById(roomId);
        if (user.getBuildingModel().equals(room.getBuilding())) {
            return true;
        } else {
            return false;
        }


    }
}
