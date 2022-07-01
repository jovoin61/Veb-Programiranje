package com.example.veb.dto;

import com.example.veb.model.Artikal;
import com.example.veb.model.Komentar;
import com.example.veb.model.Lokacija;
import com.example.veb.model.Restoran;

import java.util.ArrayList;
import java.util.List;

public class PrikazRestoranaDto {

    private String naziv;
    private String tip;
    private Lokacija lokacija;
    private Double ocena;
    private List<KomentarDto> komentari = new ArrayList<>();
    private List<Artikal> artikli = new ArrayList<>();

    public PrikazRestoranaDto() {
    }

    public PrikazRestoranaDto(Restoran restoran) {
        this.naziv = restoran.getNaziv();
        this.tip = restoran.getTip();
        this.lokacija = restoran.getLokacija();
        for(Artikal a : restoran.getArtikli()) {
            this.artikli.add(a);
        }
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

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public List<KomentarDto> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<KomentarDto> komentari) {
        this.komentari = komentari;
    }

    public List<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<Artikal> artikli) {
        this.artikli = artikli;
    }
}
