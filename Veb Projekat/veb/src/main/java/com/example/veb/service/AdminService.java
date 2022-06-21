package com.example.veb.service;

import com.example.veb.dto.KorisnikDto;
import com.example.veb.model.Korisnik;
import com.example.veb.model.Menadzer;
import com.example.veb.model.Restoran;
import com.example.veb.repository.KorisnikRepository;
import com.example.veb.repository.MenadzerRepository;
import com.example.veb.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    RestoranRepository restoranRepository;

    @Autowired
    MenadzerRepository menadzerRepository;

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

    public List<KorisnikDto> prikaz_svih_korisnika(){
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDto> korisniciDto = new ArrayList<>();

        for(Korisnik k : korisnici){
            korisniciDto.add(new KorisnikDto(k));
        }

        return korisniciDto;
    }
}
