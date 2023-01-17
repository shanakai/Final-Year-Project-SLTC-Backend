package com.vis.vis.api;

import com.vis.vis.api.entity.Admins;
import com.vis.vis.api.entity.Routes;
import com.vis.vis.api.repository.AdminRepository;
import com.vis.vis.api.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Component
public class VisApiApplication implements ApplicationRunner {
    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    private final RouteRepository routeRepository;

    public VisApiApplication(AdminRepository adminRepository, RouteRepository routeRepository) {
        this.adminRepository = adminRepository;
        this.routeRepository = routeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(VisApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Admins admin = new Admins();

        routeRepository.deleteAll();

        List<Routes> route = new ArrayList<Routes>();
        route.add(new Routes(1, "kadawatha", "Malabe", "200"));
        route.add(new Routes(2, "Kadawatha", "Kothalawala", "300"));
        route.add(new Routes(3, "Kadawatha", "Makubura", "400"));
        route.add(new Routes(4, "Kandy", "Kottawa", "100"));
        route.add(new Routes(5, "homagama", "Matara", "500"));

        try {
            for (int i = 0; i < route.size(); i++) {
                routeRepository.save(route.get(i));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        admin.setEmail("admin@vis.com");
        admin.setPassword("admin123");
        if (!adminRepository.existsByEmail(admin.getEmail())) {
            adminRepository.save(admin);
        }

    }
}
