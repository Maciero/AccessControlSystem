package com.example.accesscontrolsystem.model;

import com.example.accesscontrolsystem.model.AccessList;
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
    private BuildingModel building;

    @Enumerated(value = EnumType.STRING)
    private AccessList zone;

    @ElementCollection(targetClass = Departments.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    private List<Departments> departments;

}
/*
 @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_building",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id")
    )

 */
