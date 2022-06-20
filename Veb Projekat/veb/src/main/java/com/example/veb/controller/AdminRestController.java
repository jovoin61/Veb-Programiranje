package com.example.veb.controller;

import com.example.veb.model.Korisnik;
import com.example.veb.model.Restoran;
import com.example.veb.model.Uloga;
import com.example.veb.repository.KorisnikRepository;
import com.example.veb.repository.RestoranRepository;
import com.example.veb.service.AdminService;
import com.example.veb.service.DostavljacService;
import com.example.veb.service.MenadzerService;
import com.example.veb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminRestController {

    @Autowired
    AdminService adminService;

    @Autowired
    MenadzerService menadzerService;

    @Autowired
    DostavljacService dostavljacService;




    @PostMapping("/admin/dodavanje/menadzer")
    public ResponseEntity dodavanje_menadzera(@RequestBody Korisnik korisnik, HttpSession session) {

        Korisnik ulogovani_korisnik = (Korisnik) session.getAttribute("korisnik");
        if(ulogovani_korisnik == null || ulogovani_korisnik.getUloga() != Uloga.ADMIN){
            return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
        }
        String response = menadzerService.dodaj_menadzera(korisnik);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/dodavanje/dostavljac")
    public ResponseEntity dodavanje_dostavljaca(@RequestBody Korisnik korisnik, HttpSession session) {

        Korisnik ulogovani_korisnik = (Korisnik) session.getAttribute("korisnik");
        if(ulogovani_korisnik == null || ulogovani_korisnik.getUloga() != Uloga.ADMIN){
            return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
        }
        String response = dostavljacService.dodaj_dostavljaca(korisnik);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/dodavanje/restoran")
    public ResponseEntity dodavanje_restorana(@RequestBody Restoran restoran, HttpSession session){
        Korisnik ulogovani_korisnik = (Korisnik) session.getAttribute("korisnik");
        if(ulogovani_korisnik == null || ulogovani_korisnik.getUloga() != Uloga.ADMIN){
            return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
        }
        String response = adminService.napravi_restoran(restoran);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/restoran/{naziv_restorana}/dodaj-menadzera")
    public ResponseEntity dodaj_menadzera(@PathVariable (value="naziv_restorana") String naziv_restorana, @RequestBody String korisnicko_ime_menadzera, HttpSession session){
        Korisnik ulogovani_korisnik = (Korisnik) session.getAttribute("korisnik");
        if(ulogovani_korisnik == null || ulogovani_korisnik.getUloga() != Uloga.ADMIN){
            return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
        }
        String response = adminService.dodaj_menadzera_restoranu(naziv_restorana, korisnicko_ime_menadzera);

        return ResponseEntity.ok(response);
    }
}
