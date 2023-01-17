package com.vis.vis.api.service;

import com.vis.vis.api.entity.Users;

import java.util.List;

public interface UserService {

    Users registerUser(Users user);
    List<Users> allUsers();

    void delete(long id);
}
