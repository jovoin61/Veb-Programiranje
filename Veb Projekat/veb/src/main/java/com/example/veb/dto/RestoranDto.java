package com.example.veb.dto;

import com.example.veb.model.*;

import java.util.HashSet;
import java.util.Set;

public class RestoranDto {
    private String naziv;
    private String tip;
    private Lokacija lokacija;
    private StatusRestorana statusRestorana;
    private Set<Komentar> komentari;

    public RestoranDto() {
    }

    public RestoranDto(Restoran restoran){
        this.naziv = restoran.getNaziv();
        this.tip = restoran.getTip();
        this.lokacija = restoran.getLokacija();
        this.statusRestorana = restoran.getStatusRestorana();
        this.komentari = restoran.getKomentari();

    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public StatusRestorana getStatusRestorana() {
        return statusRestorana;
    }

    public void setStatusRestorana(StatusRestorana statusRestorana) {
        this.statusRestorana = statusRestorana;
    }
}
