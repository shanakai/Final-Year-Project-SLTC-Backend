package com.vis.vis.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Records {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;


    private String vehiclenumber;


    private String checkin;

    private String userid;

    @Value("${some.key:false}")
    private boolean issCheckout;


    private String checkout;


    private String inDateTime;

    private String outDateTime;

    private String price;

    private String paymentStatus;


}
