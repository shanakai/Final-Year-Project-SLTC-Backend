package com.vis.vis.api.service.impl;

import com.vis.vis.api.entity.Users;
import com.vis.vis.api.repository.UserRepository;
import com.vis.vis.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

   private UserRepository userRepository;

    @Override
    public Users registerUser(Users user) {
        Users newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public List<Users> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
