package com.example.veb.service;


import com.example.veb.dto.*;
import com.example.veb.model.*;
import com.example.veb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestoranSerevice {

    @Autowired
    private RestoranRepository restoranRepository;
    @Autowired
    private MenadzerRepository menadzerRepository;
    @Autowired
    private KomentarRepository komentarRepository;
    @Autowired
    private ArtikalRepository artikalRepository;
    @Autowired
    private KpgsRepository kpgsRepository;

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

    public boolean dodajArtikal(ArtikalDto artikalDto,Long idRestorana){
        Optional<Restoran> r = restoranRepository.findById(idRestorana);
        if(!r.isPresent())
            return false;

        Restoran restoran = restoranRepository.getById(idRestorana);

        Menadzer menadzer = menadzerRepository.getByRestoran(restoran);
        if(menadzer == null) return false;

        Artikal artikal = new Artikal(artikalDto.getNaziv(),artikalDto.getCena(),artikalDto.getTip(),artikalDto.getKolicina(), artikalDto.getOpis());
        restoran.getArtikli().add(artikal);
        artikal.setRestoran(restoran);
        artikalRepository.save(artikal);

        return true;
    }
    public boolean azurirajArtikal(ArtikalDto artikalDto , Long idArtikla,Long idRestorana, Menadzer menadzer){

        Optional<Artikal> artikalOptional =artikalRepository.findById(idArtikla);
        if(!artikalOptional.isPresent()) return false;
        Optional<Restoran> restoranOptional =restoranRepository.findById(idRestorana);
        if(!restoranOptional.isPresent()) return false;

        boolean provera = false;
        Restoran restoran = restoranRepository.getById(idRestorana);
        Artikal artikal = artikalRepository.getById(idArtikla);

        for(Artikal a : restoran.getArtikli()){
            if(a.getId()==artikal.getId()) provera = true;
        }
        if(!provera) return false;

        if(menadzer.getRestoran().getId() !=restoran.getId()) return false;

        artikal.setCena(artikalDto.getCena());
        artikal.setKolicina(artikalDto.getKolicina());
        artikal.setNaziv(artikalDto.getNaziv());
        artikal.setOpis(artikalDto.getOpis());
        artikal.setTip(artikalDto.getTip());
        artikalRepository.save(artikal);

        return true;
    }
    public boolean obrisiArtikal( Long idArtikla,Long idRestorana, Menadzer menadzer){

        System.out.println("1");
        Optional<Artikal> artikalOptional =artikalRepository.findById(idArtikla);
        if(!artikalOptional.isPresent()) return false;
        Optional<Restoran> restoranOptional =restoranRepository.findById(idRestorana);
        if(!restoranOptional.isPresent()) return false;
        System.out.println("2");
        boolean provera = false;
        Restoran restoran = restoranRepository.getById(idRestorana);
        Artikal artikal = artikalRepository.getById(idArtikla);

        for(Artikal a : restoran.getArtikli()){
            if(a.getId()==artikal.getId()) provera = true;
        }
        System.out.println("3");
        if(!provera) return false;
        System.out.println("4");
        if(menadzer.getRestoran().getId() !=restoran.getId()) return false;
        System.out.println("5");

        List<Kpgs> p = kpgsRepository.findAll();
        for(Kpgs kpgs : p ){
            //if(kpgs.getArtikal().getId() == artikal.getId())
               // kpgs.setArtikal(null);
               // kpgsRepository.delete(kpgs);
        }
        if(restoran.getArtikli().contains(artikal)) {
            restoran.getArtikli().remove(artikal);
            artikalRepository.delete(artikal);
            restoranRepository.save(restoran);
            return true;
        }



        return false;
    }
}
