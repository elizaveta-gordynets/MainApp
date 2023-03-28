package com.example.mainapp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class OTP {

    @Id
    private String userId;
    private String code;

}
