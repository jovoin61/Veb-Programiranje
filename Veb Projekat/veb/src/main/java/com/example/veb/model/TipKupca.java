package com.example.veb.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipKupca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tip_kupca;

    @Column
    private String ime;

    @Column
    private Integer popust;

    @Column
    private Integer trazeni_broj_bodova;

    public TipKupca() {
    }

    public TipKupca(String ime, Integer popust, Integer trazeni_broj_bodova) {
        this.ime = ime;
        this.popust = popust;
        this.trazeni_broj_bodova = trazeni_broj_bodova;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Integer getPopust() {
        return popust;
    }

    public void setPopust(Integer popust) {
        this.popust = popust;
    }

    public Integer getTrazeni_broj_bodova() {
        return trazeni_broj_bodova;
    }

    public void setTrazeni_broj_bodova(Integer trazeni_broj_bodova) {
        this.trazeni_broj_bodova = trazeni_broj_bodova;
    }

    public Long getId_tip_kupca() {
        return id_tip_kupca;
    }

    public void setId_tip_kupca(Long id_tip_kupca) {
        this.id_tip_kupca = id_tip_kupca;
    }
}
