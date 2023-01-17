package com.vis.vis.api.repository;

import com.vis.vis.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Boolean existsByEmail(String email);
    Users findByEmail(String email);
}
