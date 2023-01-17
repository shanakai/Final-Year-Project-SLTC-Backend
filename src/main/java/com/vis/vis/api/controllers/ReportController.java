package com.vis.vis.api.controllers;

import com.vis.vis.api.entity.Records;
import com.vis.vis.api.entity.Routes;
import com.vis.vis.api.entity.Vehicles;
import com.vis.vis.api.repository.RecordsRepository;
import com.vis.vis.api.repository.RouteRepository;
import com.vis.vis.api.repository.VehiclesRepository;
import com.vis.vis.api.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/records")
public class ReportController {

    @Autowired
    private RecordsService recordsService;

    @Autowired
    private RecordsRepository recordsRepository;

    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("/all")
    public List<Records> getAll() {
        return recordsRepository.findAll();
    }

    @GetMapping("/userRecords/")
    public ResponseEntity<?> getUserReports(@RequestParam("oid") String id) {
        //Todo logics
        List<Records> records = recordsRepository.getRecordsByUserid(id);
        if (records.size() < 1) {
            return new ResponseEntity<>("No Records found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @GetMapping("/vehile/")
    public ResponseEntity<?> getOwnerVehicles(@RequestParam("vnum") String vnum) {
        //Todo logics
        Records records = recordsRepository.getRecordsByVehiclenumber(vnum);
        if (records == null) {
            return new ResponseEntity<>("No Record found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @PostMapping("/in")
    public ResponseEntity<Records> addInRecord(@RequestBody Records record) {
        Vehicles vehicle = null != vehiclesRepository.findVehiclesByVehicleNumber(record.getVehiclenumber()) ? vehiclesRepository.findVehiclesByVehicleNumber(record.getVehiclenumber()) : null;
        String uid = null != vehicle ? String.valueOf(vehicle.getOwnerId()) : "N/A";
        record.setUserid(uid);
        record.setInDateTime(formatDate());
        return new ResponseEntity<Records>(recordsService.addRecord(record), HttpStatus.CREATED);
    }

    @PostMapping("/out")
    public ResponseEntity<?> addOutRecord(@RequestBody Records record) {

        Records r = recordsRepository.getRecordsByVehiclenumberAndCheckout(record.getVehiclenumber(), null);
        if (r == null) {
            return new ResponseEntity<>("Vehicle entry record Not Found", HttpStatus.NOT_FOUND);
        }
        Routes route = routeRepository.getRoutesByCheckinAndCheckout(r.getCheckin(), record.getCheckout());

        r.setCheckout(record.getCheckout());
        r.setPrice(route.getPrice());
        r.setPaymentStatus("Pending");
        r.setOutDateTime(formatDate());
        return new ResponseEntity<Records>(recordsRepository.save(r), HttpStatus.OK);
    }




    @PutMapping("/add")
    public ResponseEntity<Records> updateRecord(@RequestBody Records record) {

        return new ResponseEntity<Records>(recordsService.addRecord(record), HttpStatus.CREATED);
    }

    @PostMapping("/payment")
    private void updatePayment(@RequestParam("id") String id){
        System.out.println(Long.valueOf(id));
        Records record = recordsRepository.getOne(Long.valueOf(id));
        record.setPaymentStatus("Success");
        recordsRepository.save(record);
    }

    String formatDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);

        return formattedDate;
    }

}
