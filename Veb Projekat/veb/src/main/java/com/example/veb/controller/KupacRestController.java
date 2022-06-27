package com.example.veb.controller;

import com.example.veb.model.Kupac;
import com.example.veb.model.Porudzbina;
import com.example.veb.model.Uloga;
import com.example.veb.service.PorudzbinaService;
import com.example.veb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpSession;
import java.util.List;

public class KupacRestController {

    @Autowired
    SessionService sessionService;
    @Autowired
    PorudzbinaService porudzbinaService;

    @GetMapping("/kupac/porudzbine")
    public ResponseEntity<?> ispisi_porudzbine( HttpSession session){
        if(sessionService.da_li_je_korisnik(Uloga.KUPAC, session)){
            String kupac = (String) session.getAttribute("korisnickoIme");
            return new ResponseEntity<List<Porudzbina>>(porudzbinaService.prikaz_svih_porudzbina(kupac), HttpStatus.OK);
        }
        return new ResponseEntity<>("Niste Kupac", HttpStatus.UNAUTHORIZED);
    }

}
