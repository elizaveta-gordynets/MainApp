package com.example.mainapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class VacancyDto implements Serializable {

    private String id;
    private String header;
    private String description;
    private String expirationDate;
    private String location;
    private String startDate;
}
