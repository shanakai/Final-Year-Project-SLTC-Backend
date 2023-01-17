package com.vis.vis.api.repository;

import com.vis.vis.api.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admins, Long> {

    Boolean existsByEmail(String email);
    Admins findByEmail(String email);
}
