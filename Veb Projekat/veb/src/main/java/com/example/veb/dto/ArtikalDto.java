package com.example.veb.dto;

import com.example.veb.model.Artikal;
import com.example.veb.model.Tip;

public class ArtikalDto {

    private String naziv;
    private Double cena;
    private Tip tip;
    private String opis;
    private Integer kolicina;

    public ArtikalDto() {
    }

    public ArtikalDto(Artikal a) {
        this.naziv = a.getNaziv();
        this.cena = a.getCena();
        this.tip = a.getTip();
        this.opis = a.getOpis();
        this.kolicina = a.getKolicina();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }
}
