package com.example.veb.controller;

import com.example.veb.dto.RegistracijaDto;
import com.example.veb.model.Kupac;
import com.example.veb.service.RegistracijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class RegistracijaRestController {

    @Autowired
    private RegistracijaService registracijaService;

    @PostMapping("/registracija")
    public ResponseEntity registracija(@RequestBody RegistracijaDto registracijaDto){

        HashMap<String, String> greska = provera(registracijaDto);

        if(!greska.isEmpty()) return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        Kupac kupac = registracijaDto.konvertuj_u_kupca();

        try{
            registracijaService.registracija_kupca(kupac);
        } catch (Exception e){
            greska.put("korisnickoIme", e.getMessage());
        }

        if(!greska.isEmpty()) return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        return new ResponseEntity("USPEŠNA REGISTRACIJA", HttpStatus.OK);
    }

    private HashMap<String, String> provera(RegistracijaDto rd) {

        HashMap<String,String> greska = new HashMap<>();

        if(rd.getIme() == null || rd.getIme().isEmpty()){
            greska.put("ime", "Polje ime je obavezno.");
        }
        if(rd.getIme().length() > 20){
            greska.put("ime", "Ime mora biti kraće od 20 znakova.");
        }


        if(rd.getPrezime() == null || rd.getPrezime().isEmpty()){
            greska.put("prezime", "Polje prezime je obavezno.");
        }
        if(rd.getPrezime().length() > 20){
            greska.put("prezime", "Prezime mora biti kraće od 20 znakova.");
        }


        if(rd.getKorisnickoIme() == null || rd.getKorisnickoIme().isEmpty()){
            greska.put("korisnickoIme", "Polje korisnicko ime je obavezno.");
        }
        if(rd.getKorisnickoIme().length() > 15 || rd.getKorisnickoIme().length() < 4){
            greska.put("korisnickoIme", "Korisnicko ime mora biti kraće od 15 znakova i duže od 4 znaka.");
        }


        if(rd.getLozinka() == null || rd.getLozinka().isEmpty()){
            greska.put("lozinka", "Polje lozinka je obavezno.");
        }
        if(rd.getLozinka().length() > 20 || rd.getLozinka().length() < 6){
            greska.put("lozinka", "lozinka mora biti kraća od 20 znakova i duža od 6 znaka.");
        }


        if(rd.getDatumRodjenja() == null ){
            greska.put("datum", "Polje datum je obavezno.");
        }


        if(rd.getPol() == null ){
            greska.put("pol", "Polje pol je obavezno.");
        }

        return greska;
    }
}
