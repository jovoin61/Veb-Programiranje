package com.example.veb.service;

import com.example.veb.model.Korisnik;
import com.example.veb.model.Kupac;
import com.example.veb.model.Uloga;
import com.example.veb.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistracijaService {

    @Autowired
    private KupacRepository kupacRepository;

    public void registracija_kupca(Kupac kupac) throws Exception {

        provera_kupca(kupac.getKorisnickoIme(), kupacRepository.findAll());

        kupac.setUloga(Uloga.KUPAC);
        kupacRepository.save(kupac);
    }

    private void provera_kupca(String korisnickoIme, List<Kupac> listaKorisnika) throws Exception {
        for(Korisnik k : listaKorisnika){
            if(k.getKorisnickoIme().equals(korisnickoIme)){
                pogresno_korisnicko_ime(korisnickoIme);
            }
        }
    }

    private void pogresno_korisnicko_ime(String korisnickoIme) throws Exception {
        throw new Exception("Korisnik sa imenom '" + korisnickoIme + "' vec postoji!");
    }
}
