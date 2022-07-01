package com.example.veb.controller;

import com.example.veb.dto.PorudzbinaDto;
import com.example.veb.model.Dostavljac;
import com.example.veb.model.Korisnik;
import com.example.veb.model.Kupac;
import com.example.veb.model.Uloga;
import com.example.veb.service.PorudzbinaService;
import com.example.veb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class PorudzbinaRestController {

    @Autowired
    SessionService sessionService;
    @Autowired
    PorudzbinaService porudzbinaService;

    @GetMapping("/porudzbine/kupac")
    public ResponseEntity<List<PorudzbinaDto>> ispisi_porudzbine_kupac(HttpSession session){
        if (sessionService.da_li_je_korisnik(Uloga.KUPAC, session)){

            Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
            /*if(korisnik == null){
                System.out.println("Nema sesije!");
                return new ResponseEntity<>("Nema sesije!",HttpStatus.OK);
            }*/
            return new ResponseEntity<List<PorudzbinaDto>>(porudzbinaService.prikaz_svih_porudzbina_korisnik(korisnik), HttpStatus.OK);
        }
        return new ResponseEntity("Niste Kupac", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/porudzbine/dostavljac")
    public ResponseEntity<List<PorudzbinaDto>> ispisi_porudzbine_dostavljac(HttpSession session){

        //if(logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.DOSTAVLJAC)
        if( sessionService.da_li_je_korisnik(Uloga.DOSTAVLJAC, session)){
            Dostavljac dostavljac = (Dostavljac)session.getAttribute("korisnik");
            return new ResponseEntity<List<PorudzbinaDto>>(porudzbinaService.prikaz_svih_porudzbina_dostavljac(dostavljac),HttpStatus.OK);

        }
        //List<PorudzbinaDto> porudzbinas = porudzbinaService.PorudzbineDostavljaca(logovaniKorisnik);
        //return ResponseEntity.ok(porudzbinas);
        return new ResponseEntity("Niste Dostavljac", HttpStatus.FORBIDDEN);
    }
}
