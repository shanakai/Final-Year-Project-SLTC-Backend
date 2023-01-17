package com.vis.vis.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"nic"})
})
public class Users {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @NotNull
    private String firstName;

    private String lastName;
    @NotNull
    private String nic;

    private String address;
    @NotNull
    @Email
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

    private final String type = "user";

}
