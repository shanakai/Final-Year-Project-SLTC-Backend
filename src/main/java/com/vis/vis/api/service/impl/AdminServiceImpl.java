package com.vis.vis.api.service.impl;

import com.vis.vis.api.entity.Admins;
import com.vis.vis.api.repository.AdminRepository;
import com.vis.vis.api.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    @Override
    public Admins addAdmin(Admins admin) {
        Admins newAdmin = adminRepository.save(admin);
        return newAdmin;
    }

    @Override
    public List<Admins> getAll() {
        return adminRepository.findAll();
    }
}
