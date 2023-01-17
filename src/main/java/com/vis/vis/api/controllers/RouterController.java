package com.vis.vis.api.controllers;

import com.vis.vis.api.entity.Routes;
import com.vis.vis.api.entity.Vehicles;
import com.vis.vis.api.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("route")
public class RouterController {

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("/all")
    public List<Routes> all(){
        return routeRepository.findAll();
    }

    @GetMapping("/byCheckinout/")
    public ResponseEntity<?> getOwnerVehicles(@RequestParam("in") String cin,@RequestParam("out") String cout ) {
        System.out.println(cin);
        //Todo logics
        Routes route = routeRepository.getRoutesByCheckinAndCheckout(cin,cout);
        if (route == null) {
            return new ResponseEntity<>("No Route found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(route, HttpStatus.OK);
    }
}
