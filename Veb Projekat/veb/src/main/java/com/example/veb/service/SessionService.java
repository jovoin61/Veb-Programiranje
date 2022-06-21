package com.example.veb.service;

import com.example.veb.model.Korisnik;
import com.example.veb.model.Uloga;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {
    public Boolean da_li_je_korisnik(Uloga uloga, HttpSession session){
        Korisnik ulogovani_korisnik = (Korisnik) session.getAttribute("korisnik");
        if(ulogovani_korisnik == null || ulogovani_korisnik.getUloga() != uloga){
            return false;
        }
        return true;
    }
}
