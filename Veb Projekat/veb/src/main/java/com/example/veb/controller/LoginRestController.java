package com.example.veb.controller;

import com.example.veb.dto.LoginDto;
import com.example.veb.model.Korisnik;
import com.example.veb.service.LoginService;
import com.example.veb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;

@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionService sessionService;

    @CrossOrigin
    @PostMapping("/prijava")
    public ResponseEntity<?> prijava(@RequestBody LoginDto loginDto, HttpSession session){

        Hashtable<String, String> greska = new Hashtable<>();

        if (loginDto.getKorisnickoIme() == null || loginDto.getKorisnickoIme().isEmpty()) {
            greska.put("korisnickoIme", "Morate uneti korisnicko ime.");
        }

        if (loginDto.getLozinka() == null || loginDto.getLozinka().isEmpty()) {
            greska.put("lozinka", "Morate uneti lozinku");
        }

        if (!greska.isEmpty()) {
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);
        }

        Korisnik ulogovaniKorisnik = null;

        try {
            ulogovaniKorisnik = loginService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        } catch (AccountNotFoundException e) {
            greska.put("korisnickoIme", e.getMessage());
        }

        if(!greska.isEmpty() || ulogovaniKorisnik == null) {
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("korisnik", ulogovaniKorisnik);
        session.setAttribute("korisnickoIme", ulogovaniKorisnik.getKorisnickoIme());
        session.setAttribute("uloga", ulogovaniKorisnik.getUloga());

        return  new ResponseEntity(ulogovaniKorisnik, HttpStatus.OK);
    }

    @PostMapping("/odjava")
    public ResponseEntity odjava(HttpSession session){
        if(!sessionService.proveri(session)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        session.invalidate();

        return new ResponseEntity("Uspešno ste se odjavili", HttpStatus.OK);
    }
}
