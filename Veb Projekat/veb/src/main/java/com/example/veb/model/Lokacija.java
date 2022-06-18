package com.example.veb.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lokacije;

    @Column
    private Double geografska_duzina;

    @Column
    private Double geografska_sirina;

    @Column
    private String adresa;

    public Lokacija() {
    }

    public Lokacija(Double geografska_duzina, Double geografska_sirina, String adresa) {
        this.geografska_duzina = geografska_duzina;
        this.geografska_sirina = geografska_sirina;
        this.adresa = adresa;
    }

    public Double getGeografska_duzina() {
        return geografska_duzina;
    }

    public void setGeografska_duzina(Double geografska_duzina) {
        this.geografska_duzina = geografska_duzina;
    }

    public Double getGeografska_sirina() {
        return geografska_sirina;
    }

    public void setGeografska_sirina(Double geografska_sirina) {
        this.geografska_sirina = geografska_sirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Long getId_lokacije() {
        return id_lokacije;
    }

    public void setId_lokacije(Long id_lokacije) {
        this.id_lokacije = id_lokacije;
    }


}
