package com.example.veb.controller;

import com.example.veb.model.Korisnik;
import com.example.veb.model.Menadzer;
import com.example.veb.model.Uloga;
import com.example.veb.repository.RestoranRepository;
import com.example.veb.service.MenadzerService;
import com.example.veb.service.RestoranSerevice;
import com.example.veb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MenadzerRestController {

    @Autowired
    SessionService sessionService;

    @Autowired
    MenadzerService menadzerService;

    @Autowired
    RestoranRepository restoranRepository;

    @GetMapping("/menadzer/restoran")
    public ResponseEntity<?> prikaz_restorana_porudzbina(HttpSession session){
        if(!sessionService.proveri(session)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(sessionService.da_li_je_korisnik(Uloga.MENADZER, session)){
            Menadzer menadzer = (Menadzer) session.getAttribute("korisnik");
            return new ResponseEntity(menadzer.getRestoran(), HttpStatus.OK);
        }

        return new ResponseEntity("NISTE MENADZER", HttpStatus.OK);
    }
}
