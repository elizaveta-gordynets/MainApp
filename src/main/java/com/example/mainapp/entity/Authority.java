package com.example.mainapp.entity;

import com.example.mainapp.dto.Roles;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority {

    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    @Column
    private Roles name;

    public Authority (Roles role) {
        this.name = role;
    }
}
