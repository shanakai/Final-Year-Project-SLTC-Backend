package com.vis.vis.api.controllers;

import com.vis.vis.api.entity.Vehicles;
import com.vis.vis.api.repository.VehiclesRepository;
import com.vis.vis.api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String test() {
        return "Test vehicle";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVehicle(@Valid @RequestBody Vehicles vehicle) {
        //Todo logics

        return new ResponseEntity<>(vehicleService.addVehicle(vehicle), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        //Todo logics
        List<Vehicles> vehicles = vehicleService.getAll();
        if (vehicles.size() <= 0) {
            return new ResponseEntity<>("No vehicles found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/owners/")
    public ResponseEntity<?> getOwnerVehicles(@RequestParam("oid") String id) {
        System.out.println(id);
        //Todo logics
        List<Vehicles> vehicles = vehicleService.getOwnersVehicles(Long.parseLong(id));
        if (vehicles.size() <= 0) {
            return new ResponseEntity<>("No vehicles found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }


    @DeleteMapping("/delete/")
    public void deleteByName(@RequestParam("vid") long id) {
        vehicleService.deleteVehicle(id);
    }
}
