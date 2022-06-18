package com.example.veb.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Kpgs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_kpgs;

    @ManyToOne
    private Artikal artikal;

    private Double ukupna_cena;

    @Column
    private Integer broj_artikala;

    public Kpgs() {
    }

    public Kpgs(Artikal artikal, Integer broj_artikala) {
        this.artikal = artikal;
        this.broj_artikala = broj_artikala;
        this.ukupna_cena = artikal.getCena() * broj_artikala;
    }

    public Double getUkupna_cena() {
        return ukupna_cena;
    }

    public void setUkupna_cena(Double ukupna_cena) {
        this.ukupna_cena = ukupna_cena;
    }

    public Long getId_kpgs() {
        return id_kpgs;
    }

    public void setId_kpgs(Long id_kpgs) {
        this.id_kpgs = id_kpgs;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Integer getBroj_artikala() {
        return broj_artikala;
    }

    public void setBroj_artikala(Integer broj_artikala) {
        this.broj_artikala = broj_artikala;
    }
}
