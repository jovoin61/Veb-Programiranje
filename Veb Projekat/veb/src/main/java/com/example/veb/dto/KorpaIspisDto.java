package com.example.veb.dto;

import com.example.veb.model.Artikal;
import com.example.veb.model.Kpgs;

public class KorpaIspisDto {

    private Artikal artikal;
    private Integer BrojArtikala;
    private Double UkupnaCena;


    public KorpaIspisDto() {
    }

    public KorpaIspisDto(Kpgs kpgs) {
        this.artikal = kpgs.getArtikal();
        this.BrojArtikala = kpgs.getBrojArtikala();
        this.UkupnaCena = kpgs.getUkupnaCena();
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Integer getBrojArtikala() {
        return BrojArtikala;
    }

    public void setBrojArtikala(Integer brojArtikala) {
        BrojArtikala = brojArtikala;
    }

    public Double getUkupnaCena() {
        return UkupnaCena;
    }

    public void setUkupnaCena(Double ukupnaCena) {
        UkupnaCena = ukupnaCena;
    }
}
