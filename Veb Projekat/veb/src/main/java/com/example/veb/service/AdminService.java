package com.example.veb.service;

import com.example.veb.model.Menadzer;
import com.example.veb.model.Restoran;
import com.example.veb.repository.KorisnikRepository;
import com.example.veb.repository.MenadzerRepository;
import com.example.veb.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    RestoranRepository restoranRepository;

    @Autowired
    MenadzerRepository menadzerRepository;

    public String napravi_restoran(Restoran restoran){
        restoranRepository.save(restoran);

        return "DODAT NOVI RESTORAN!";
    }

    public String dodaj_menadzera_restoranu(String naziv_restorana, String korisnicko_ime_menadzera){

        Menadzer menadzer = menadzerRepository.findByKorisnickoIme(korisnicko_ime_menadzera);
        Restoran restoran = restoranRepository.findByNaziv(naziv_restorana);
        if(menadzer != null && restoran != null){
            menadzer.setRestoran(restoran);
            menadzerRepository.save(menadzer);
            return "MENADZER JE DODAT";
        }
        return "POKUŠAJTE PONOVO";
    }
}
