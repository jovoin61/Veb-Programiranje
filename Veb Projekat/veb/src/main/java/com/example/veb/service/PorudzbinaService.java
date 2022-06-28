package com.example.veb.service;

import com.example.veb.dto.PorudzbinaDto;
import com.example.veb.model.Korisnik;
import com.example.veb.model.Kupac;
import com.example.veb.model.Porudzbina;
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
    KupacService kupacService;

    public List<PorudzbinaDto> prikaz_svih_porudzbina(Korisnik korisnik){

        Kupac kupac = kupacService.findKupac(korisnik);


        Set<Porudzbina> porudzbine = porudzbinaRepository.getByKupac(kupac);
       // Set<PorudzbinaDto> porudzbinaDtos = new HashSet<>();
        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();
        for(Porudzbina p : porudzbine){
            PorudzbinaDto temp = new PorudzbinaDto(p);
            porudzbinaDtoList.add(temp);
        }


        return porudzbinaDtoList;
    }
}
