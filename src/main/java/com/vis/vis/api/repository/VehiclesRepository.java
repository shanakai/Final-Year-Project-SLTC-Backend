package com.vis.vis.api.repository;

import com.vis.vis.api.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiclesRepository extends JpaRepository<Vehicles, Long> {

    List<Vehicles> findByOwnerId(long id);
    Vehicles findVehiclesByVehicleNumber(String id);

}
