package com.example.veb.service;

import com.example.veb.model.Dostavljac;
import com.example.veb.model.Korisnik;
import com.example.veb.model.Menadzer;
import com.example.veb.repository.DostavljacRepository;
import com.example.veb.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DostavljacService {
    @Autowired
    private DostavljacRepository dostavljacRepository;

    public String dodaj_dostavljaca(Korisnik korisnik){
        Dostavljac noviDostavljac = new Dostavljac(korisnik);
        dostavljacRepository.save(noviDostavljac);

        return "DODAT NOVI DOSTAVLJAC";
    }
}
