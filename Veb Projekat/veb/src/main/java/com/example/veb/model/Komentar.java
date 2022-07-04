package com.example.veb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kupac_id")
    @JsonIgnore
    private Kupac kupac;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restoran_id")
    @JsonIgnore
    private Restoran restoran;

    @Column
    private String tekstKomentara;

    @Column
    private Integer ocena;

    public Komentar() {
    }

    public Komentar(Kupac kupac, Restoran restoran, String tekstKomentara, Integer ocena) {
        this.kupac = kupac;
        this.restoran = restoran;
        this.tekstKomentara = tekstKomentara;
        this.ocena = ocena;
    }
    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getTekst_komentara() {
        return tekstKomentara;
    }

    public void setTekst_komentara(String tekst_komentara) {
        this.tekstKomentara = tekst_komentara;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


}
