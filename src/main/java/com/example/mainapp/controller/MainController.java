package com.example.mainapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/home")
    public ResponseEntity<?> getStartPage() {

        return ResponseEntity.ok("This is start page");
    }

    @GetMapping("/main")
    public ResponseEntity<?> getMainPage() {

    return new ResponseEntity<>("Wow man page", HttpStatus.OK);
    }
}
