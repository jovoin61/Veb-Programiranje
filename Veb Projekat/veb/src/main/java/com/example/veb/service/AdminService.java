package com.example.veb.service;

import com.example.veb.dto.KorisnikDto;
import com.example.veb.model.Korisnik;
import com.example.veb.model.Menadzer;
import com.example.veb.model.Porudzbina;
import com.example.veb.model.Restoran;
import com.example.veb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    RestoranRepository restoranRepository;
    @Autowired
    MenadzerRepository menadzerRepository;
    @Autowired
    PorudzbinaRepository porudzbinaRepository;
    @Autowired
    KpgsRepository kpgsRepository;

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

    public List<KorisnikDto> prikaz_svih_korisnika_ime(String ime){
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDto> korisniciDto = new ArrayList<>();

        for(Korisnik k : korisnici){
            if(k.getIme().equals(ime))
            korisniciDto.add(new KorisnikDto(k));
        }
        return korisniciDto;
    }
    public List<KorisnikDto> prikaz_svih_korisnika_prezime(String ime){
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDto> korisniciDto = new ArrayList<>();

        for(Korisnik k : korisnici){
            if(k.getPrezime().equals(ime))
                korisniciDto.add(new KorisnikDto(k));
        }
        return korisniciDto;
    }
    public List<KorisnikDto> prikaz_svih_korisnika_korisnicko_ime(String ime){
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDto> korisniciDto = new ArrayList<>();

        for(Korisnik k : korisnici){
            if(k.getKorisnickoIme().equals(ime))
                korisniciDto.add(new KorisnikDto(k));
        }
        return korisniciDto;
    }

    public boolean obrisiRestoran(Long idRestorana){

        Optional<Restoran> r = restoranRepository.findById(idRestorana);
        if(!r.isPresent())
            return false;
        Restoran omg = restoranRepository.getById(idRestorana);


        List<Porudzbina> p = porudzbinaRepository.findAll();
        for (Porudzbina pp : p){
            if(pp.getRestoran()!=null){
                if(pp.getRestoran().equals(omg))
                    pp.setRestoran(null);
            }

        }
        Menadzer menadzer = menadzerRepository.getByRestoran(omg);
        if(menadzer != null){
            menadzer.setRestoran(null);
        }

        restoranRepository.delete(omg);

        return true;
    }
    public boolean dvaKorisnickaImena (String korisnickoIme){
        List<Korisnik> korisnikList = korisnikRepository.findAll();

        for(Korisnik k : korisnikList){
            if(k.getKorisnickoIme().equals(korisnickoIme)) return false;
        }
        return true;
    }
}
