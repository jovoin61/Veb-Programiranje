package com.example.veb.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipKupca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ime;

    @Column
    private Integer popust;

    @Column
    private Integer trazeniBrojBodova;

    public TipKupca() {
    }

    public TipKupca(String ime, Integer popust, Integer trazeniBrojBodova) {
        this.ime = ime;
        this.popust = popust;
        this.trazeniBrojBodova = trazeniBrojBodova;
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

    public Integer getTrazeniBrojBodova() {
        return trazeniBrojBodova;
    }

    public void setTrazeniBrojBodova(Integer trazeni_broj_bodova) {
        this.trazeniBrojBodova = trazeni_broj_bodova;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
