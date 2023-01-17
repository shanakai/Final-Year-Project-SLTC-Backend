package com.vis.vis.api.service;

import com.vis.vis.api.entity.Admins;

import java.util.List;

public interface AdminService {

    Admins addAdmin(Admins admin);
    List<Admins> getAll();
}
