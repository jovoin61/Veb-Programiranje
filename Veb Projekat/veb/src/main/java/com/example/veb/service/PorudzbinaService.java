package com.example.veb.service;

import com.example.veb.dto.PorudzbinaDto;
import com.example.veb.model.*;
import com.example.veb.repository.DostavljacRepository;
import com.example.veb.repository.KupacRepository;
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
    KupacRepository kupacRepository;
    @Autowired
    PorudzbinaRepository porudzbinaRepository;
    @Autowired
    DostavljacRepository dostavljacRepository;

    public List<PorudzbinaDto> prikaz_svih_porudzbina_korisnik(Korisnik korisnik){

        Kupac kupac = kupacRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());


        Set<Porudzbina> porudzbine = porudzbinaRepository.getByKupac(kupac);
       // Set<PorudzbinaDto> porudzbinaDtos = new HashSet<>();
        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();
        for(Porudzbina p : porudzbine){
            PorudzbinaDto temp = new PorudzbinaDto(p);
            porudzbinaDtoList.add(temp);
        }


        return porudzbinaDtoList;
    }
    public List<PorudzbinaDto> prikaz_svih_porudzbina_dostavljac(Korisnik korisnik){
        List<Porudzbina> porudzbinas= porudzbinaRepository.findAll();
        List<PorudzbinaDto> porudzbinaDtos = new ArrayList<>();

        for(Porudzbina p : porudzbinas){
            System.out.println("JovoCar");
            if(p.getStatus() == Status.CEKA_DOSTAVLJACA){
                System.out.println("PusiKuracc");
                PorudzbinaDto temp = new PorudzbinaDto(p);
                porudzbinaDtos.add(temp);
            }
        }
        System.out.println("volim kurac");

        Dostavljac dostavljac = dostavljacRepository.findByKorisnickoIme(korisnik.getKorisnickoIme());
        if(dostavljac==null){
            System.out.println("Mrs u picku materinu");
        }
        Set<Porudzbina> porudzbinaList = dostavljac.getPorudzbine();
        if(porudzbinaList.isEmpty()){
            System.out.println("da");
        }
        for (Porudzbina p : dostavljac.getPorudzbine()){
            PorudzbinaDto temp = new PorudzbinaDto(p);
            System.out.println("PusiKurac");
            porudzbinaDtos.add(temp);
        }

        return porudzbinaDtos;


    }
}
