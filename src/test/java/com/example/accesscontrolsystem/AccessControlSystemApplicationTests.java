package com.example.accesscontrolsystem;

import com.example.accesscontrolsystem.model.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccessControlSystemApplicationTests {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidUserModel() {
        UserModel userModel = new UserModel();
        userModel.setName("John");
        userModel.setSurname("Doe");
        userModel.setDepartment(Departments.IT);
        userModel.setPositions(Positions.MANAGER);

        List<BuildingModel> buildings = new ArrayList<>();
        BuildingModel building = new BuildingModel();
        buildings.add(building);
        userModel.setBuildingModels(buildings);

        List<AccessList> accessList = new ArrayList<>();
        accessList.add(AccessList.SECURE);
        userModel.setAccessList(accessList);

        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);
        assertThat(violations).isEmpty();
    }

    @Test
    public void testInvalidUserModel() {
        UserModel userModel = new UserModel();
        userModel.setDepartment(null); // Invalid: Department is required

        List<AccessList> accessList = new ArrayList<>();
        userModel.setAccessList(accessList); // Invalid: Access List is required


        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);
        assertThat(violations).hasSize(2);
    }

    @Test
    public void testValidBuildingModels() {
        UserModel userModel = new UserModel();
        BuildingModel building = new BuildingModel();
        List<BuildingModel> buildings = new ArrayList<>();
        buildings.add(building);
        userModel.setBuildingModels(buildings);

        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);
        assertThat(violations).isEmpty();
    }

    @Test
    public void testInvalidBuildingModels() {
        UserModel userModel = new UserModel();
        List<BuildingModel> buildings = new ArrayList<>();
        userModel.setBuildingModels(buildings); // Invalid: Building Models is empty

        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);
        assertThat(violations).hasSize(1);
    }
}
