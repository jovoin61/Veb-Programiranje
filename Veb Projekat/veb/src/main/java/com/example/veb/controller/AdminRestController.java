package com.example.veb.controller;

import com.example.veb.dto.KorisnikDto;
import com.example.veb.model.Korisnik;
import com.example.veb.model.Restoran;
import com.example.veb.model.Uloga;
import com.example.veb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminRestController {

    @Autowired
    AdminService adminService;

    @Autowired
    MenadzerService menadzerService;

    @Autowired
    DostavljacService dostavljacService;

    @Autowired
    RestoranSerevice restoranSerevice;

    @Autowired
    SessionService sessionService;


    @PostMapping("/admin/dodavanje/menadzer")
    public ResponseEntity dodavanje_menadzera(@RequestBody Korisnik korisnik, HttpSession session) {
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            //String response = menadzerService.dodaj_menadzera(korisnik);
            return new ResponseEntity(menadzerService.dodaj_menadzera(korisnik), HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/admin/dodavanje/dostavljac")
    public ResponseEntity dodavanje_dostavljaca(@RequestBody Korisnik korisnik, HttpSession session) {
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            String response = dostavljacService.dodaj_dostavljaca(korisnik);
            return new ResponseEntity(response, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/admin/dodavanje/restoran")
    public ResponseEntity dodavanje_restorana(@RequestBody Restoran restoran, HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            String response = restoranSerevice.dodaj_restoran(restoran);
            return new ResponseEntity(response, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/admin/restoran/{naziv_restorana}/dodaj-menadzera")
    public ResponseEntity dodavanje_menadzera_restoranu(@PathVariable (value="naziv_restorana") String naziv_restorana, @RequestBody String korisnicko_ime_menadzera, HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            String response = adminService.dodaj_menadzera_restoranu(naziv_restorana, korisnicko_ime_menadzera);
            return new ResponseEntity(response, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/admin/korisnici")
    public ResponseEntity<List<KorisnikDto>> korisnici(HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            List<KorisnikDto> korisnici = adminService.prikaz_svih_korisnika();
            return new ResponseEntity<List<KorisnikDto>>(korisnici, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);

    }
}
