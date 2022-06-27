package com.example.veb.controller;


import com.example.veb.dto.RestoranDto;
import com.example.veb.repository.RestoranRepository;
import com.example.veb.service.RestoranSerevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranSerevice restoranSerevice;

    @Autowired
    private RestoranRepository restoranRepository;

    @GetMapping("/")
    public List<RestoranDto> svi_restorani() {
        List<RestoranDto> restorani = restoranSerevice.svi_restorani();

        return restorani;
    }
}
