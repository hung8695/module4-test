package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.repository.ICityRepository;
import com.example.demo.service.city.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping("")
    public ResponseEntity<Iterable<City>> getList() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> save(@Valid @RequestBody City city) {
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }
@GetMapping("/{id}")
public ResponseEntity<City> findOne(@PathVariable Long id){
        return new ResponseEntity<>(cityService.findById(id).get(),HttpStatus.OK);
}
    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @RequestBody City city) {
        Optional<City> city1 = cityService.findById(id);
        if (!city1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setId(city1.get().getId());
        cityService.save(city);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteBlog(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remove(id);
        return new ResponseEntity<>(city.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<City>> search(@RequestParam("name") String name){
        Iterable<City> cities ;
        if(name.equals("")){
            cities = cityService.findAll();
        } else {
            cities = cityService.findByNameContaining(name);
        }
        return new ResponseEntity<>(cities,HttpStatus.OK);
    }
}
