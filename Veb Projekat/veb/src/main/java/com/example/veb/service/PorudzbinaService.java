package com.example.veb.service;

import com.example.veb.model.Kupac;
import com.example.veb.model.Porudzbina;
import com.example.veb.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PorudzbinaService {

    @Autowired
    KupacRepository kupacRepository;


    public List<Porudzbina> prikaz_svih_porudzbina(String korisnickoIme){
        Kupac kupac = kupacRepository.findByKorisnickoIme(korisnickoIme);
        List<Porudzbina> porudzbine = new ArrayList<>();
        Set<Porudzbina> porudzbinaSet = kupac.getPorudzbine();
        for (Porudzbina p : porudzbinaSet){
            porudzbine.add(p);
        }
        return porudzbine;
    }
}
