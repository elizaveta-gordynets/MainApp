package com.example.mainapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vacancy {

    @Id
    private String id;
    private String header;
    private String description;
    private LocalDate expirationDate;
    private String location;
    private LocalDate startDate;
    private boolean isOutdated;
}
