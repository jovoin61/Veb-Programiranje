package com.example.veb.dto;

import com.example.veb.model.Restoran;

public class PretragaRestoranDto {
    private String naziv = null;
    private String tip = null;
    private String lokacija = null;

    public PretragaRestoranDto() {
    }

    public PretragaRestoranDto(String naziv, String tip, String lokacija) {
        this.naziv = naziv;
        this.tip = tip;
        this.lokacija = lokacija;
    }

    public PretragaRestoranDto(Restoran restoran) {
        this.naziv = restoran.getNaziv();
        this.tip = restoran.getTip();
        this.lokacija = restoran.getLokacija().getAdresa();
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

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
}
