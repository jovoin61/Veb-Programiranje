package com.example.veb.controller;

import com.example.veb.dto.KorisnikDto;
import com.example.veb.dto.RestoranDto;
import com.example.veb.model.Korisnik;
import com.example.veb.model.Menadzer;
import com.example.veb.model.Restoran;
import com.example.veb.model.Uloga;
import com.example.veb.repository.RestoranRepository;
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

    @Autowired
    RestoranRepository restoranRepository;


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
    public ResponseEntity<?> dodavanje_restorana(@RequestBody RestoranDto restoranDto, HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            String poruka;
            ResponseEntity<String> kreirajRestoran;
            Restoran restoran = new Restoran(restoranDto);

            try {
                restoranSerevice.dodaj_restoran(restoranDto);
            } catch (Exception e) {
                poruka = "Restoran vec postoji.";
                kreirajRestoran = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(poruka);
            }

            try {
                restoranRepository.saveAndFlush(restoran);
                poruka = "Restoran uspesno kreiran.";
                kreirajRestoran = ResponseEntity.ok(poruka);
            } catch (Exception e) {
                poruka = "Neuspesno kreiranje restorana, pokusajte ponovo...";
                System.out.println(e.getMessage());
                kreirajRestoran = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(poruka);
            }

            return kreirajRestoran;
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
