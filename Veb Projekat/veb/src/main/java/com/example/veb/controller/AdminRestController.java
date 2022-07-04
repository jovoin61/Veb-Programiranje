package com.example.veb.controller;

import com.example.veb.dto.ArtikalDto;
import com.example.veb.dto.KorisnikDto;
import com.example.veb.dto.RestoranDto;
import com.example.veb.model.*;
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
    private AdminService adminService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private RestoranSerevice restoranSerevice;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private LokacijaService lokacijaService;

    @CrossOrigin
    @PostMapping("/admin/dodavanje/menadzer")
    public ResponseEntity dodavanje_menadzera(@RequestBody Korisnik korisnik, HttpSession session) {
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            return new ResponseEntity(menadzerService.dodaj_menadzera(korisnik), HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
    }

    @CrossOrigin
    @PostMapping("/admin/dodavanje/dostavljac")
    public ResponseEntity dodavanje_dostavljaca(@RequestBody Korisnik korisnik, HttpSession session) {
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            String response = dostavljacService.dodaj_dostavljaca(korisnik);
            return new ResponseEntity(response, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
    }

    @CrossOrigin
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

    @CrossOrigin
    @PostMapping("admin/restoran/{naziv_restorana}/dodaj-lokaciju")
    public ResponseEntity dodavanje_lokacije_restoranu(@PathVariable (value="naziv_restorana") String naziv_restorana, @RequestBody Lokacija lokacija, HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            String response = lokacijaService.dodaj_lokaciju(lokacija, naziv_restorana);
            return new ResponseEntity(response, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
    }

    @CrossOrigin
    @PostMapping("/admin/restoran/{naziv_restorana}/dodaj-menadzera")
    public ResponseEntity dodavanje_menadzera_restoranu(@PathVariable (value="naziv_restorana") String naziv_restorana, @RequestBody String korisnicko_ime_menadzera, HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            String response = adminService.dodaj_menadzera_restoranu(naziv_restorana, korisnicko_ime_menadzera);
            return new ResponseEntity(response, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);
    }

    @CrossOrigin
    @GetMapping("/admin/korisnici")
    public ResponseEntity<List<KorisnikDto>> korisnici(HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            List<KorisnikDto> korisnici = adminService.prikaz_svih_korisnika();
            return new ResponseEntity<List<KorisnikDto>>(korisnici, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);

    }

    @CrossOrigin
    @GetMapping("/admin/korisnici/ime/{ime}")
    public ResponseEntity<List<KorisnikDto>> korisniciIme(@PathVariable (value="ime") String name,HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            List<KorisnikDto> korisnici = adminService.prikaz_svih_korisnika_ime(name);
            return new ResponseEntity<List<KorisnikDto>>(korisnici, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);

    }

    @CrossOrigin
    @GetMapping("/admin/korisnici/korisnickoime/{ime}")
    public ResponseEntity<List<KorisnikDto>> korisniciKorisnickoIme(@PathVariable (value="ime") String name,HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            List<KorisnikDto> korisnici = adminService.prikaz_svih_korisnika_korisnicko_ime(name);
            return new ResponseEntity<List<KorisnikDto>>(korisnici, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);

    }

    @CrossOrigin
    @GetMapping("/admin/korisnici/prezime/{ime}")
    public ResponseEntity<List<KorisnikDto>> korisniciprezime(@PathVariable (value="ime") String name,HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){
            List<KorisnikDto> korisnici = adminService.prikaz_svih_korisnika_prezime(name);
            return new ResponseEntity<List<KorisnikDto>>(korisnici, HttpStatus.OK);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);

    }

    @CrossOrigin
    @DeleteMapping("/admin/restoran/{idR}")
    public ResponseEntity obrisiRestoran(@PathVariable (value ="idR") long idRestorana, HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.ADMIN, session)){

            if(adminService.obrisiRestoran(idRestorana))
                return new ResponseEntity("Uspesno brisanje", HttpStatus.OK);

            return new ResponseEntity("Doslo je do greske..", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Niste ADMIN!", HttpStatus.FORBIDDEN);

    }


}
