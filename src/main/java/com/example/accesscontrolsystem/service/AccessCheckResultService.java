package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.model.AccessCheckResultModel;
import com.example.accesscontrolsystem.model.RoomModel;
import com.example.accesscontrolsystem.repository.AccessCheckResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccessCheckResultService {
    private final AccessCheckResultRepository accessCheckResultRepository;

    public void addAccessCheckResult(AccessCheckResultModel acr) {
        accessCheckResultRepository.save(acr);
    }

    public List<AccessCheckResultModel> getResults() {
        return accessCheckResultRepository.findAll();
    }

    public AccessCheckResultModel getResultById(Long id) {
        return accessCheckResultRepository.findById(id).orElse(null);
    }

    public void saveEditResult(AccessCheckResultModel acr) {
        accessCheckResultRepository.save(acr);
    }

    public void removeResult(Long id) {
        accessCheckResultRepository.deleteById(id);
    }

    public void removeOldestTen(){
      getResults().stream()
              .sorted(Comparator.comparing(u -> u.getCreationDate()))
              .limit(10)
              .collect(Collectors.toList())
              .forEach(e-> removeResult(e.getId()));
    }
    public void removeAll(){
        accessCheckResultRepository.deleteAll();
    }

    public void sortResults(List<AccessCheckResultModel> res, String sortBy) {
        if (sortBy != null) {
            switch (sortBy) {
                case "id":
                    res.sort(Comparator.comparing(AccessCheckResultModel::getId));
                    break;
                case "creationDate":
                    res.sort(Comparator.comparing(u -> u.getCreationDate().toString()));
                    break;
                case "description":
                    res.sort(Comparator.comparing(e -> e.getDescription().toLowerCase()));
                    break;
                default:
                    // Obsłuż nieznany parametr sortowania
                    break;
            }
        }
    }

    public String getACRCount(List<AccessCheckResultModel> acr) {
        return "Total number of records: " + acr.size();
    }
}
