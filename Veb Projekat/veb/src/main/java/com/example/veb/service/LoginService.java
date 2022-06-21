package com.example.veb.service;

import com.example.veb.model.Korisnik;
import com.example.veb.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik login(String korisnickoIme, String lozinka) throws AccountNotFoundException {
        Korisnik korisnik = pronadjiKorisnika(korisnickoIme, (List<Korisnik>) korisnikRepository.findAll());

        if(korisnik == null){
            throw new AccountNotFoundException("Ne postoji unet korisnik.");
        }

        if(!korisnik.getLozinka().equals(lozinka)){
            throw new AccountNotFoundException("Pograsna lozinka.");
        }

        return korisnik;
    }

    private Korisnik pronadjiKorisnika(String korisnickoIme, List<Korisnik> listaKorisnika) {
        for (Korisnik k : listaKorisnika) {
            if (k.getKorisnickoIme().equals(korisnickoIme))
                return k;
        }

        return null;
    }
}
