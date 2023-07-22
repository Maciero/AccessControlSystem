package com.example.accesscontrolsystem.model;

import com.example.accesscontrolsystem.AccessList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


//    @NotBlank(message = "Email is mandatory")

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "department")
    private Departments department;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_building",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "building_id")
    )
    private List<BuildingModel> buildingModels = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "buildingModel_id")
//    private BuildingModel buildingModel;

    @ElementCollection(targetClass = AccessList.class, fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private List<AccessList> accessList;



}
