package com.vis.vis.api.service;

import com.vis.vis.api.entity.Vehicles;

import java.util.List;

public interface VehicleService {
    Vehicles addVehicle(Vehicles vehicle);
    List<Vehicles> getOwnersVehicles(long ownerId);

    List<Vehicles> getAll();

    void deleteVehicle(long id);
}
