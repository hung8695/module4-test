package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private ICountryService countryService;
    @GetMapping("")
    public ResponseEntity<Iterable<Country>> getListCountry(){
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }
}
