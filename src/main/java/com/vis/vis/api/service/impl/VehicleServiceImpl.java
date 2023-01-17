package com.vis.vis.api.service.impl;

import com.vis.vis.api.entity.Vehicles;
import com.vis.vis.api.repository.VehiclesRepository;
import com.vis.vis.api.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    private VehiclesRepository vehiclesRepository;
    @Override
    public Vehicles addVehicle(Vehicles vehicle) {
        Vehicles newVehicle = vehiclesRepository.save(vehicle);
        return newVehicle;
    }

    @Override
    public List<Vehicles> getOwnersVehicles(long ownerId) {

        return vehiclesRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Vehicles> getAll() {
        return vehiclesRepository.findAll();
    }

    @Override
    public void deleteVehicle(long id) {
        vehiclesRepository.deleteById(id);
    }
}
