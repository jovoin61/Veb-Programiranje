package com.example.veb.controller;

import com.example.veb.dto.PorudzbinaDto;
import com.example.veb.model.Korisnik;
import com.example.veb.model.Uloga;
import com.example.veb.service.PorudzbinaService;
import com.example.veb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
public class PorudzbinaRestController {

    @Autowired
    SessionService sessionService;
    @Autowired
    PorudzbinaService porudzbinaService;

    @GetMapping("/porudzbine")
    public ResponseEntity<Set<PorudzbinaDto>> ispisi_porudzbine(HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.KUPAC, session)){

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
            /*if(korisnik == null){
                System.out.println("Nema sesije!");
                return new ResponseEntity<>("Nema sesije!",HttpStatus.OK);
            }*/
            return new ResponseEntity<Set<PorudzbinaDto>>(porudzbinaService.prikaz_svih_porudzbina(korisnik), HttpStatus.OK);
        }
        return new ResponseEntity("Niste Kupac", HttpStatus.FORBIDDEN);
    }

}
