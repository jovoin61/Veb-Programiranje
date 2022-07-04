package com.example.veb.controller;


import com.example.veb.dto.ArtikalDto;
import com.example.veb.dto.PretragaRestoranDto;
import com.example.veb.dto.PrikazRestoranaDto;
import com.example.veb.dto.RestoranDto;
import com.example.veb.model.*;
import com.example.veb.repository.*;
import com.example.veb.service.RestoranSerevice;
import com.example.veb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    private SessionService sessionService;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @CrossOrigin
    @GetMapping("/")
    public List<RestoranDto> svi_restorani() {
        List<RestoranDto> restorani = restoranSerevice.svi_restorani();

        return restorani;
    }
    @CrossOrigin
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

    @CrossOrigin
    @GetMapping("restoran/pretraga")
    public ResponseEntity<?>  pretraga_restorana(@RequestBody PretragaRestoranDto prd, HttpSession session){
        List<Restoran> restorani = restoranSerevice.pretraga(prd);

        if(restorani.isEmpty()) return new ResponseEntity<>("Ne postoji dati restoran", HttpStatus.OK);

        return new ResponseEntity<>(restorani, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("menadzer/restoran/{idR}/dodavanje/artikal")
    public ResponseEntity dodajArtikal(@PathVariable(name ="idR") Long idRestorana,@RequestBody ArtikalDto artikalDto , HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.MENADZER, session)){
            if(restoranSerevice.dodajArtikal(artikalDto,idRestorana))
                return new ResponseEntity("Artikal je dodat", HttpStatus.OK);
            return new ResponseEntity("Doslo je do greske prilikom dodavanja artikla" , HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity("Niste Menadzer!", HttpStatus.FORBIDDEN);

    }

    @CrossOrigin
    @PutMapping("menadzer/azuriraj/restoran/{idR}/artikal/{idA}")
    public ResponseEntity azurirajArtikal(@PathVariable(name ="idA") Long idArtikla,@PathVariable(name ="idR") Long idRestorana,@RequestBody ArtikalDto artikalDto , HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.MENADZER, session)){

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

            Menadzer menadzer = menadzerRepository.getById(korisnik.getId());

            if(restoranSerevice.azurirajArtikal(artikalDto,idArtikla,idRestorana, menadzer))
                return new ResponseEntity("Artikal je izmenjen", HttpStatus.OK);
            return new ResponseEntity("Doslo je do greske prilikom izmene artikla" , HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity("Niste Menadzer!", HttpStatus.FORBIDDEN);

    }

    @CrossOrigin
    @DeleteMapping("menadzer/obrisi/restoran/{idR}/artikal/{idA}")
    public ResponseEntity obrisiArtikal(@PathVariable(name ="idA") Long idArtikla,@PathVariable(name ="idR") Long idRestorana, HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.MENADZER, session)){

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

            Menadzer menadzer = menadzerRepository.getById(korisnik.getId());

            if(restoranSerevice.obrisiArtikal(idArtikla,idRestorana, menadzer))
                return new ResponseEntity("Artikal je obrisan", HttpStatus.OK);
            return new ResponseEntity("Doslo je do greske prilikom izmene artikla" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Niste Menadzer!", HttpStatus.FORBIDDEN);
    }

}