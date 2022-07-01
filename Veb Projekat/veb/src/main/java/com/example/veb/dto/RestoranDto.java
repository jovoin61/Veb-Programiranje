package com.example.veb.dto;

import com.example.veb.model.Artikal;
import com.example.veb.model.Lokacija;
import com.example.veb.model.Restoran;

import java.util.HashSet;
import java.util.Set;

public class RestoranDto {
    private String naziv;
    private String tip;
    private Lokacija lokacija;


    public RestoranDto() {
    }

    public RestoranDto(Restoran restoran){
        this.naziv = restoran.getNaziv();
        this.tip = restoran.getTip();
        this.lokacija = restoran.getLokacija();

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
}
