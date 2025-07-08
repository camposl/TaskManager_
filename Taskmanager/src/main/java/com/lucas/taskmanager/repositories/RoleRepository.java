package com.lucas.taskmanager.repositories;

import com.lucas.taskmanager.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Additional query methods can be defined here if needed
}
