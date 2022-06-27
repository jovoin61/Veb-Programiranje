package com.example.veb.service;


import com.example.veb.dto.RestoranDto;
import com.example.veb.model.Restoran;
import com.example.veb.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class RestoranSerevice {

    @Autowired
    private RestoranRepository restoranRepository;

    public void dodaj_restoran(RestoranDto restoranDto) throws Exception {
        Restoran restoran = new Restoran(restoranDto);
        invalid(restoran.getNaziv(), restoranRepository.findAll());
    }

    private void invalid(String naziv, List<Restoran> restoranList) throws Exception {
        for (Restoran r : restoranList)
            if (r.getNaziv().equals(naziv))
                pogresan_naziv(naziv);
    }

    private void pogresan_naziv(String naziv) throws Exception {
        throw new Exception("Restoran sa imenom '" + naziv + "' vec postoji!");
    }

    public Restoran save(Restoran restoran) {
        return restoranRepository.save(restoran);
    }

    public List<RestoranDto> svi_restorani(){
        List<Restoran> restorani = restoranRepository.findAll();
        List<RestoranDto> restoraniDto = new ArrayList<>();

        for(Restoran r : restorani){
            restoraniDto.add(new RestoranDto(r));
        }
        return restoraniDto;
    }
}
