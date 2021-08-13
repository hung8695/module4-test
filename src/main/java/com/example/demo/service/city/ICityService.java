package com.example.demo.service.city;

import com.example.demo.model.City;
import com.example.demo.service.IGeneralService;

public interface ICityService extends IGeneralService<City> {
    Iterable<City> findByNameContaining(String name);
}
