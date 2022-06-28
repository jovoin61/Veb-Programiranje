package com.example.veb.service;

import com.example.veb.model.Korisnik;
import com.example.veb.model.Kupac;
import com.example.veb.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KupacService {
    @Autowired
    KupacRepository kupacRepository;

    public Kupac findKupac(Korisnik korisnik){
        Kupac kupac = kupacRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());
        return kupac;
    }

    public void saveKupc(Kupac kupac){
        kupacRepository.save(kupac);
    }
}

