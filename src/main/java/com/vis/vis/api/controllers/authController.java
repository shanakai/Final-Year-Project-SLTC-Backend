package com.vis.vis.api.controllers;

import com.vis.vis.api.Dto.AuthDto;
import com.vis.vis.api.entity.Admins;
import com.vis.vis.api.entity.Users;
import com.vis.vis.api.repository.AdminRepository;
import com.vis.vis.api.repository.UserRepository;
import com.vis.vis.api.service.AdminService;
import com.vis.vis.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class authController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    public String hi() {
        return "Hello world";
    }

    @PostMapping("/user/signup/")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Users user) {
        //todo logic
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(" Email allready taken", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);

    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userService.allUsers();
    }

    @PostMapping("/admin/add/")
    public ResponseEntity<?> addAdmin(@Valid @RequestBody Admins admin) {
        //todo logic
        if (adminRepository.existsByEmail(admin.getEmail())) {
            return new ResponseEntity<>(" Email all ready taken", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(adminService.addAdmin(admin), HttpStatus.CREATED);

    }
    @GetMapping("/admins")
    public List<Admins> getAllAdmins() {
        return adminRepository.findAll();
    }
    @PostMapping("user/login")
    public ResponseEntity<?> userLogin(@RequestBody AuthDto authDto) {

        if (userRepository.existsByEmail(authDto.getEmail())) {
            Users user = userRepository.findByEmail(authDto.getEmail());
            if (user.getPassword().equals(authDto.getPassword())) {
                return new ResponseEntity<Users>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Password is wrong. Please check", HttpStatus.FORBIDDEN);
            }

        }
        if (!userRepository.existsByEmail(authDto.getEmail())) {
            return new ResponseEntity<>("Email is Invalid.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Email is Invalid.", HttpStatus.CONFLICT);
    }

    @PostMapping("admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody AuthDto authDto) {

        if (adminRepository.existsByEmail(authDto.getEmail())) {
            Admins admin = adminRepository.findByEmail(authDto.getEmail());
            if (admin.getPassword().equals(authDto.getPassword())) {
                return new ResponseEntity<Admins>(admin, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Password is wrong. Please check", HttpStatus.FORBIDDEN);
            }

        }
        if (!userRepository.existsByEmail(authDto.getEmail())) {
            return new ResponseEntity<>("Email is Invalid.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Email is Invalid.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/user/delete/")
    public void deleteByName(@RequestParam("vid") long id) {
        userService.delete(id);
    }
}
