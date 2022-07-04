package com.example.veb.controller;

import com.example.veb.dto.KorisnikDto;
import com.example.veb.dto.RegistracijaDto;
import com.example.veb.model.Korisnik;
import com.example.veb.repository.KorisnikRepository;
import com.example.veb.service.KorisnikService;
import com.example.veb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private SessionService sessionService;

    @CrossOrigin
    @GetMapping("/prikaz-profila")
    public ResponseEntity<Korisnik> prikaz_profila(HttpSession session){
        if(!sessionService.proveri(session)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/izmena-profila")
    public ResponseEntity<Korisnik> izmena_profila(@RequestBody RegistracijaDto registracijaDto, HttpSession session){
       if(!sessionService.proveri(session)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        korisnik = korisnikService.izmena_profila(registracijaDto, korisnik);

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/korisnik")
    public ResponseEntity<KorisnikDto> boliMeDupe(HttpSession session){
        if(!sessionService.proveri(session)){
            return new ResponseEntity("Niste ulogovani",HttpStatus.FORBIDDEN);
        }
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        KorisnikDto korisnikDto = new KorisnikDto(korisnik);
        return new ResponseEntity<>(korisnikDto,HttpStatus.OK);

    }
}
