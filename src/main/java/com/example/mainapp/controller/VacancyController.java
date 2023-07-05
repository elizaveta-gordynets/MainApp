package com.example.mainapp.controller;

import com.example.mainapp.dto.VacancyDto;
import com.example.mainapp.entity.Vacancy;
import com.example.mainapp.service.VacancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {

    private final VacancyService vacancyService;

    public VacancyController(VacancyService service) {
        this.vacancyService = service;
    }

    @GetMapping("/{id}")
    public Vacancy getVacancy(@PathVariable("id") String id) {
        return vacancyService.getVacancy(id);
    }

    @PostMapping(consumes = "application/json")
    public void createVacancy(@RequestBody VacancyDto vacancyDto) {
        vacancyService.createVacancy(vacancyDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable("id") String id,
                                                 @RequestBody VacancyDto vacancyDto) {
        if(vacancyService.vacancyExistsById(id)) {
            vacancyDto.setId(id);
            return ResponseEntity.ok(vacancyService.updateVacancy(vacancyDto));
        }
        return ResponseEntity.notFound().build();
    }

}
