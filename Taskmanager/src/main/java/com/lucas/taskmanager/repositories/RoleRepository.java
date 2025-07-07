package com.lucas.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Role extends JpaRepository<Role, Long> {

    // Additional query methods can be defined here if needed
}
