package com.example.accesscontrolsystem.repository;

import com.example.accesscontrolsystem.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {
}
