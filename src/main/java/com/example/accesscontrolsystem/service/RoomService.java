package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.model.RoomModel;
import com.example.accesscontrolsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public void addRoom(RoomModel user) {
        roomRepository.save(user);
    }

    public List<RoomModel> getRoomList() {
        return roomRepository.findAll();
    }

    public RoomModel getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public void saveEditRoom(RoomModel room) {
        roomRepository.save(room);
    }

    public void removeRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
