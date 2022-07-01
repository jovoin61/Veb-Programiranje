package com.example.veb.service;

import com.example.veb.model.Korisnik;
import com.example.veb.model.Uloga;
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

    public String getKorisnickoIme(HttpSession session) {

        Object korisnickoIme = session.getAttribute("korisnickoIme");
        if (korisnickoIme == null) {
            return "";
        }

        if (korisnickoIme.toString().isEmpty()) {
            return "";
        }

        return korisnickoIme.toString();
    }

    public Uloga getUloga(HttpSession session) {

        Object uloga = session.getAttribute("uloga");

        if (uloga == null) {
            return null;
        }

        if (uloga.toString().isEmpty()){
            return null;
        }

        return (Uloga) uloga;
    }

    public boolean proveri(HttpSession session) {
        if(session == null){
            return false;
        }

        String korisnickoIme = getKorisnickoIme(session);
        Uloga uloga = getUloga(session);

        if (korisnickoIme == null || korisnickoIme.isEmpty())
            return false;

        return !uloga.toString().isEmpty();
    }
}
