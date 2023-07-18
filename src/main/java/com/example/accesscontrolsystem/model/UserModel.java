package com.example.accesscontrolsystem.model;

import com.example.accesscontrolsystem.AccessList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Departaments department;

    @ManyToOne
    @JoinColumn(name = "buildingModel_id")
    private BuildingModel buildingModel;

    @Enumerated(value = EnumType.STRING)
    private AccessList accessList;



}
