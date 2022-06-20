package com.example.veb.service;

import com.example.veb.model.Korisnik;
import com.example.veb.model.Menadzer;
import com.example.veb.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    public String dodaj_menadzera(Korisnik korisnik){
        Menadzer noviMenadzer = new Menadzer(korisnik);
        menadzerRepository.save(noviMenadzer);

        return "DODAT NOVI MENADZER";
    }
}
