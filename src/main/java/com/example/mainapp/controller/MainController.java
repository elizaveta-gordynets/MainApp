package com.example.mainapp.controller;

import com.example.mainapp.service.VacancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final VacancyService vacancyService;

    public MainController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/home")
    public ResponseEntity<?> getStartPage() {

        return ResponseEntity.ok("This is start page");
    }

    @GetMapping("/vacancy-list")
    public ResponseEntity<?> getAllVacancies() {
            return ResponseEntity.ok(vacancyService.getAllVacancies());
    }
}
