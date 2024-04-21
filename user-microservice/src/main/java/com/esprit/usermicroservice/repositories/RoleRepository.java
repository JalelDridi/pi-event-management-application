package com.esprit.usermicroservice.repositories;

import com.esprit.usermicroservice.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, Integer> {
    Optional<Role> findByName(String roleStudent);
}
