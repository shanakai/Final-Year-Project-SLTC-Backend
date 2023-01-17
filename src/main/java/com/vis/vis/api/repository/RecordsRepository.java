package com.vis.vis.api.repository;

import com.vis.vis.api.entity.Records;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordsRepository extends JpaRepository<Records, Long> {

    List<Records> getRecordsByUserid(String userId);

    Records getRecordsByVehiclenumber(String num);

    Records getRecordsByVehiclenumberAndCheckout(String num, String cOut);


}
