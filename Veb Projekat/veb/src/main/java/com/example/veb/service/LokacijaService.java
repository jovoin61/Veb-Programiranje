package com.example.veb.service;

import com.example.veb.model.Lokacija;
import com.example.veb.model.Restoran;
import com.example.veb.repository.LokacijaRepository;
import com.example.veb.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LokacijaService {

    @Autowired
    LokacijaRepository lokacijaRepository;

    @Autowired
    RestoranRepository restoranRepository;

    public String dodaj_lokaciju(Lokacija lokacija, String nazivRestorana) {
        lokacijaRepository.save(lokacija);
        Restoran restoran = restoranRepository.findByNaziv(nazivRestorana);
        restoran.setLokacija(lokacija);
        restoranRepository.save(restoran);

        return "Lokacija je dodeljena restoranu";
    }
}
