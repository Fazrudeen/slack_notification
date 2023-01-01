package com.samplejava.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private String  userId;

    @Column(name = "first_name")
    private String  firstName;

    @Column(name = "last_name")
    private String  lastName;

    @Column(name = "email_address")
    private String  emailAddress;

    @Column(name = "address_line1")
    private String  addressLine1;

    @Column(name = "address_line2")
    private String  addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String  state;

    @Column(name = "zipcode")
    private Integer  zipcode;

}