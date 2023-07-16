package com.example.accesscontrolsystem.model;

import com.example.accesscontrolsystem.AccessList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "buildingModel_id")
    private BuildingModel buildingModel;

    @Enumerated(value = EnumType.STRING)
    private AccessList accessList;


}
