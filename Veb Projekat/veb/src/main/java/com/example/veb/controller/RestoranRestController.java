package com.example.veb.controller;


import com.example.veb.dto.PrikazRestoranaDto;
import com.example.veb.dto.RestoranDto;
import com.example.veb.model.Komentar;
import com.example.veb.model.Restoran;
import com.example.veb.repository.KomentarRepository;
import com.example.veb.repository.RestoranRepository;
import com.example.veb.service.RestoranSerevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranSerevice restoranSerevice;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    KomentarRepository komentarRepository;

    @GetMapping("/")
    public List<RestoranDto> svi_restorani() {
        List<RestoranDto> restorani = restoranSerevice.svi_restorani();

        return restorani;
    }

    @GetMapping("restoran/{id}")
    public ResponseEntity<?> prikaz_restorana(@PathVariable(name = "id") Long id){
        List<Restoran> restorani = restoranRepository.findAll();

        Restoran restoran = new Restoran();
        restoran = null;

        for (Restoran r : restorani) {
            if (Objects.equals(id, r.getId())) {
                restoran = r;
            }
        }
        if (restoran == null) return new ResponseEntity<>("Ne postoji dati restoran", HttpStatus.NO_CONTENT);

        PrikazRestoranaDto restoranZaPrikaz = new PrikazRestoranaDto(restoran);

        restoranZaPrikaz = restoranSerevice.izracunaj_ocenu(restoran);


        return new ResponseEntity(restoranZaPrikaz, HttpStatus.OK);
    }
}
