package com.example.veb.service;

import com.example.veb.dto.PorudzbinaDto;
import com.example.veb.model.*;
import com.example.veb.repository.DostavljacRepository;
import com.example.veb.repository.KupacRepository;
import com.example.veb.repository.MenadzerRepository;
import com.example.veb.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PorudzbinaService {

    @Autowired
    private KupacRepository kupacRepository;
    @Autowired
    private  PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private DostavljacRepository dostavljacRepository;
    @Autowired
    private MenadzerRepository menadzerRepository;

    public List<PorudzbinaDto> prikaz_svih_porudzbina_korisnik(Korisnik korisnik){

        Kupac kupac = kupacRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());
        Set<Porudzbina> porudzbine = porudzbinaRepository.getByKupac(kupac);
        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();

        for(Porudzbina p : porudzbine){
            PorudzbinaDto temp = new PorudzbinaDto(p);
            porudzbinaDtoList.add(temp);
        }


        return porudzbinaDtoList;
    }
    public List<PorudzbinaDto> prikaz_svih_porudzbina_dostavljac(Korisnik korisnik){
        List<PorudzbinaDto> porudzbinaDtos = new ArrayList<>();

        for(Porudzbina p : porudzbinaRepository.findAll()){
            if(p.getStatus() == Status.CEKA_DOSTAVLJACA){
                PorudzbinaDto temp = new PorudzbinaDto(p);
                porudzbinaDtos.add(temp);
            }
        }
        Dostavljac dostavljac = dostavljacRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());

        for (Porudzbina p : dostavljac.getPorudzbine()){
            PorudzbinaDto temp = new PorudzbinaDto(p);
            porudzbinaDtos.add(temp);
        }

        return porudzbinaDtos;
    }
    public List<PorudzbinaDto> prikaz_svih_porudzbina_menadzer(Korisnik korisnik){

        Menadzer menadzer = menadzerRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());
        List<PorudzbinaDto> porudzbinaDtos = new ArrayList<>();

        for(Porudzbina p : porudzbinaRepository.findAll()){
            if(p.getRestoran() == menadzer.getRestoran()){
                PorudzbinaDto temp = new PorudzbinaDto(p);
                porudzbinaDtos.add(temp);
            }
        }
        return porudzbinaDtos;
    }
}
