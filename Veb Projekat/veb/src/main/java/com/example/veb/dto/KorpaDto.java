package com.example.veb.dto;

public class KorpaDto {

    private String nazivArtikla;
    private Integer kolicina;

    public KorpaDto(){}

    public KorpaDto(String nazivArtikla, Integer kolicina){
        this.nazivArtikla = nazivArtikla;
        this.kolicina = kolicina;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }
}
