package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.model.BuildingModel;
import com.example.accesscontrolsystem.model.Positions;
import com.example.accesscontrolsystem.model.RoomModel;
import com.example.accesscontrolsystem.model.UserModel;
import com.example.accesscontrolsystem.repository.AcsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AcsService {
    private final BuildingService buildingService;
    private final UserService userService;
    private final RoomService roomService;
    private final AcsRepository acsRepository;
    private final AccessCheckResultService accessCheckResultService;


    /*

    ZAŁOŻENIA DO METOD
   void  checkAccess(){
   if(checkIfUserHasAccessToBuilding) true{


   to sprawdza strefy z access listy i sprawdza departamenty{
    1)   stwórzenie enuma ze stanowiskami i powiązanie go z userem (dyrektor, regular), natępnie metoda która sprawdza stanowisko i jak jest dyrektor to zwraca true(przyznaje dostęp)
   2) metoda która porówna accesslisty user i accessListe roomu -> musi być true żeby wszedł

   Dodatek 3) Lista nadpisań które pozwolą użytkownikowi na dostęp do pomieszczenia niezależnie od zdefiniowanych accessList

   }
     */
    public String checkAccess(Long userId, Long roomId) {   //'Access to '+ ${room.name} + ' is granted for user '+ ${selectedUser.name}+'.'"></p>
        UserModel user = userService.getUserById(userId);
        RoomModel room = roomService.getRoomById(roomId);
        if (checkPositionUser(userId)) {
            return "Access to " + room.getName() + " is granted for user " + user.getName() + ". User has access everywhere.";
        } else {
            if (checkAccessList(userId, roomId) && checkIfUserHasAccessToBuilding(userId, roomId)) {
               return  "Access to " + room.getName() + " is granted for user " + user.getName() + ".";
            } else {
                if(!checkAccessList(userId, roomId) && !checkIfUserHasAccessToBuilding(userId, roomId)){
                    return "Access to " + room.getName() + " is not granted for user " + user.getName() + ". Building and access list for this user is noncompatible.";
                } else if (!checkAccessList(userId, roomId) && checkIfUserHasAccessToBuilding(userId, roomId)){
                   return  "Access to " + room.getName() + " is not granted for user " + user.getName() + ". Access list for this user is noncompatible.";
                } else if (checkAccessList(userId, roomId) && !checkIfUserHasAccessToBuilding(userId, roomId)){
                    return  "Access to " + room.getName() + " is not granted for user " + user.getName() + ". Building for this user is noncompatible.";
                } else{
                    return "Not checked";
                }
            }
        }
    }

    public boolean checkIfUserHasAccessToBuilding(Long userId, Long roomId) {
        UserModel user = userService.getUserById(userId);
        RoomModel room = roomService.getRoomById(roomId);
        List<BuildingModel> userBuildingList = user.getBuildingModels();
        if (userBuildingList.contains(room.getBuilding())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPositionUser(Long userId) {
        UserModel user = userService.getUserById(userId);
        if (user.getPositions() == Positions.MANAGER) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkAccessList(Long userId, Long roomId) {
        UserModel user = userService.getUserById(userId);
        RoomModel room = roomService.getRoomById(roomId);
        if (user.getAccessList().contains(room.getZone())) {
            return true;
        } else {
            return false;
        }
    }
}

