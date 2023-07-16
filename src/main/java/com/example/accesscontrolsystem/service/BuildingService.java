package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.model.BuildingModel;
import com.example.accesscontrolsystem.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public void addBuilding(BuildingModel user) {
        buildingRepository.save(user);
    }

    public List<BuildingModel> getBuildingList() {
        return buildingRepository.findAll();
    }

    public BuildingModel getBuildingById(Long id) {
        return buildingRepository.findById(id).orElse(null);
    }

    public void saveEditBuilding(BuildingModel building) {
        buildingRepository.save(building);
    }

    public void removeBuilding(Long id) {
        buildingRepository.deleteById(id);
    }
}
