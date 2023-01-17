package com.vis.vis.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Vehicles {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @NotNull
    private long ownerId;


    private String ownerName;

    private String ownerNic;

    @NotNull
    private  String chacyNumber;

    @NotNull
    private String vehicleNumber;

    @NotNull
    private String vehicleType;
}
