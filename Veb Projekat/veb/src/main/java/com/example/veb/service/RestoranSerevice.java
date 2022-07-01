package com.example.veb.service;


import com.example.veb.dto.KomentarDto;
import com.example.veb.dto.PretragaRestoranDto;
import com.example.veb.dto.PrikazRestoranaDto;
import com.example.veb.dto.RestoranDto;
import com.example.veb.model.Komentar;
import com.example.veb.model.Restoran;
import com.example.veb.repository.KomentarRepository;
import com.example.veb.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RestoranSerevice {

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    KomentarRepository komentarRepository;

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

    public PrikazRestoranaDto izracunaj_ocenu(Restoran restoran){
        List<Komentar> komentars = komentarRepository.getByRestoran(restoran);
        List<KomentarDto> komentari = new ArrayList<>();
        PrikazRestoranaDto restoranZaPrikaz = new PrikazRestoranaDto(restoran);

        for(Komentar k : komentars){
            komentari.add(new KomentarDto(k));
        }

        Double ocena = 0.0;
        int brojKomentara = 0;

        if(!komentari.isEmpty()){
            for(KomentarDto k : komentari){
                ocena += k.getOcena();
                brojKomentara++;
            }
        }

        if(brojKomentara == 0 ||ocena == 0.0){
            restoranZaPrikaz.setOcena(0.0);
        } else {
            restoranZaPrikaz.setOcena(ocena/brojKomentara);
        }

        restoranZaPrikaz.setKomentari(komentari);

        return restoranZaPrikaz;
    }


    public List<Restoran> pretraga(PretragaRestoranDto prd){
        List<Restoran> sviRestorani = restoranRepository.findAll();

        Set<Restoran> poNazivu = new HashSet<>();
        Set<Restoran> poTipu = new HashSet<>();
        Set<Restoran> poAdresi = new HashSet<>();

        for(Restoran r : sviRestorani){
            if(prd.getNaziv() != null)
                if(prd.getNaziv().equals(r.getNaziv())){
                    poNazivu.add(r);
                }

            if(prd.getTip() != null)
                if(prd.getTip().equals(r.getTip())){
                    poTipu.add(r);
                }

            if(prd.getLokacija() != null)
                if(prd.getLokacija().equals(r.getLokacija().getAdresa())){
                    poAdresi.add(r);
                }
        }

        Set<Restoran> restorani = new HashSet<>();
        //111
        if(prd.getNaziv() != null && prd.getTip() != null && prd.getLokacija() != null){
            poNazivu.retainAll(poTipu);
            poNazivu.retainAll(poAdresi);

            restorani = poNazivu;
        }

        //011
        if(prd.getNaziv() == null && prd.getTip() != null && prd.getLokacija() != null){
            poTipu.retainAll(poAdresi);

            restorani = poTipu;
        }

        //001
        if(prd.getNaziv() == null && prd.getTip() == null && prd.getLokacija() != null){
            restorani = poAdresi;
        }

        //100
        if(prd.getNaziv() != null && prd.getTip() == null && prd.getLokacija() == null){
            restorani = poNazivu;
        }

        //110
        if(prd.getNaziv() != null && prd.getTip() != null && prd.getLokacija() == null){
            poNazivu.retainAll(poTipu);
            restorani = poNazivu;
        }

        //010
        if(prd.getNaziv() == null && prd.getTip() != null && prd.getLokacija() == null){
            restorani = poTipu;
            System.out.println("OVDEEEEEEEEEEEEEE");
        }

        //101
        if(prd.getNaziv() != null && prd.getTip() == null && prd.getLokacija() != null){
            poNazivu.retainAll(poAdresi);
            restorani = poNazivu;
        }

        List<Restoran> restoraniLista = new ArrayList<>();
        for(Restoran r : restorani){
            restoraniLista.add(r);
        }

        //000
        return restoraniLista;
    }
}
