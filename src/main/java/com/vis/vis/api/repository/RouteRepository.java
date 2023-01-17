package com.vis.vis.api.repository;

import com.vis.vis.api.entity.Routes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Routes, Long> {

    Routes getRoutesByCheckinAndCheckout(String checkin, String checkout);


}
