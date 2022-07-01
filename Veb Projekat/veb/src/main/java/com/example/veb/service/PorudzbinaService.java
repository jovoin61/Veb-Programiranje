package com.example.veb.service;

import com.example.veb.dto.PorudzbinaDto;
import com.example.veb.model.*;
import com.example.veb.repository.*;
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
    @Autowired
    private KpgsRepository kpgsRepository;

    public List<PorudzbinaDto> prikaz_svih_porudzbina_korisnik(Korisnik korisnik){

        Kupac kupac = kupacRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());
        Set<Porudzbina> porudzbine = porudzbinaRepository.getByKupac(kupac);
        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();
        List<Kpgs> kpgsSet = kpgsRepository.findAll();

        System.out.println("kurcina");
        System.out.println(kpgsSet);
        for(Porudzbina p : porudzbine){
            Set<Kpgs> kpgsSet1 = new HashSet<>();
            for(Kpgs k : kpgsSet){
                if(k.getPorudzbina().getUuid().compareTo(p.getUuid()) != 0) {
                    System.out.println(kpgsSet1);
                    kpgsSet1.add(k);
                }
            }

            PorudzbinaDto temp = new PorudzbinaDto(p);
            temp.setStavke(kpgsSet1);
            porudzbinaDtoList.add(temp);

        }


        return porudzbinaDtoList;
    }
    public List<PorudzbinaDto> prikaz_svih_porudzbina_dostavljac(Korisnik korisnik){
        List<PorudzbinaDto> porudzbinaDtos = new ArrayList<>();


        for(Porudzbina p : porudzbinaRepository.findAll()){
            Set<Kpgs> kpgsSet1 = new HashSet<>();
            for(Kpgs k : kpgsRepository.findAll()){
                if(k.getPorudzbina().getUuid().compareTo(p.getUuid()) != 0) {
                    kpgsSet1.add(k);
                }
            }
            if(p.getStatus() == Status.CEKA_DOSTAVLJACA){
                PorudzbinaDto temp = new PorudzbinaDto(p);
                temp.setStavke(kpgsSet1);
                porudzbinaDtos.add(temp);
            }
        }
        Dostavljac dostavljac = dostavljacRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());

        for (Porudzbina p : dostavljac.getPorudzbine()){
            Set<Kpgs> kpgsSet2 = new HashSet<>();
            for(Kpgs k : kpgsRepository.findAll()){
                if(k.getPorudzbina().getUuid().compareTo(p.getUuid()) != 0) {
                    kpgsSet2.add(k);
                }
            }
            PorudzbinaDto temp = new PorudzbinaDto(p);
            temp.setStavke(kpgsSet2);
            porudzbinaDtos.add(temp);
        }

        return porudzbinaDtos;
    }
    public List<PorudzbinaDto> prikaz_svih_porudzbina_menadzer(Korisnik korisnik){

        Menadzer menadzer = menadzerRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());
        List<PorudzbinaDto> porudzbinaDtos = new ArrayList<>();
        for(Porudzbina p : porudzbinaRepository.findAll()){


            if(p.getRestoran() == menadzer.getRestoran()){
                Set<Kpgs> kpgsSet3 = new HashSet<>();
                for(Kpgs k : kpgsRepository.findAll()){
                    if(k.getPorudzbina().getUuid().compareTo(p.getUuid()) != 0) {
                        kpgsSet3.add(k);
                    }
                }
                PorudzbinaDto temp = new PorudzbinaDto(p);
                temp.setStavke(kpgsSet3);
                porudzbinaDtos.add(temp);
            }
        }
        return porudzbinaDtos;
    }
}
