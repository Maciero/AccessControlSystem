package com.example.accesscontrolsystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.accesscontrolsystem.model.AccessList;
import com.example.accesscontrolsystem.model.Positions;
import com.example.accesscontrolsystem.model.RoomModel;
import com.example.accesscontrolsystem.model.UserModel;
import com.example.accesscontrolsystem.service.AcsService;
import com.example.accesscontrolsystem.service.RoomService;
import com.example.accesscontrolsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class AcsServiceTest {

    @Mock
    private UserService userService;


    @Mock
    private RoomService roomService;

    @InjectMocks
    private AcsService yourService; // Zastąp YourServiceClass nazwą twojej klasy serwisu

    @Test
    public void testCheckPositionUserForManager() {
        // Tworzenie przykładowego użytkownika z pozycją MANAGER
        UserModel user = new UserModel();
        user.setPositions(Positions.MANAGER);

        // Definiowanie zachowania metody getUserById(userId) w mocku userService
        when(userService.getUserById(anyLong())).thenReturn(user);

        // Wywołanie testowanej metody
        boolean result = yourService.checkPositionUser(1L);

        // Sprawdzenie czy wynik jest zgodny z oczekiwaniami
        assertTrue(result);
    }

    @Test
    public void testCheckPositionUserForNonManager() {
        // Tworzenie przykładowego użytkownika z pozycją inna niż MANAGER
        UserModel user = new UserModel();
        user.setPositions(Positions.REGULAR); // Załóżmy, że to jest inna pozycja

        // Definiowanie zachowania metody getUserById(userId) w mocku userService
        when(userService.getUserById(anyLong())).thenReturn(user);

        // Wywołanie testowanej metody
        boolean result = yourService.checkPositionUser(1L);

        // Sprawdzenie czy wynik jest zgodny z oczekiwaniami
        assertFalse(result);
    }
        @Test
        public void testCheckAccessListWithAccess() {
            // Tworzenie przykładowego użytkownika z listą dostępu zawierającą AccessList.ZONE_A
            UserModel user = new UserModel();
            user.setAccessList(Arrays.asList(AccessList.SECURE));

            // Tworzenie przykładowego pokoju z AccessList.ZONE_A
            RoomModel room = new RoomModel();
            room.setZone(AccessList.SECURE);

            // Definiowanie zachowań metod getUserById(userId) i getRoomById(roomId) w mockach userService i roomService
            when(userService.getUserById(anyLong())).thenReturn(user);
            when(roomService.getRoomById(anyLong())).thenReturn(room);

            // Wywołanie testowanej metody
            boolean result = yourService.checkAccessList(1L, 1L);

            // Sprawdzenie czy wynik jest zgodny z oczekiwaniami
            assertTrue(result);
        }

        @Test
        public void testCheckAccessListWithoutAccess() {
            // Tworzenie przykładowego użytkownika z listą dostępu nie zawierającą AccessList.ZONE_A
            UserModel user = new UserModel();
            user.setAccessList(Arrays.asList(AccessList.PUBLIC)); // Załóżmy, że to jest ina strefa

            // Tworzenie przykładowego pokoju z AccessList.ZONE_A
            RoomModel room = new RoomModel();
            room.setZone(AccessList.SECURE);

            // Definiowanie zachowań metod getUserById(userId) i getRoomById(roomId) w mockach userService i roomService
            when(userService.getUserById(anyLong())).thenReturn(user);
            when(roomService.getRoomById(anyLong())).thenReturn(room);

            // Wywołanie testowanej metody
            boolean result = yourService.checkAccessList(1L, 1L);

            // Sprawdzenie czy wynik jest zgodny z oczekiwaniami
            assertFalse(result);
        }

    }

