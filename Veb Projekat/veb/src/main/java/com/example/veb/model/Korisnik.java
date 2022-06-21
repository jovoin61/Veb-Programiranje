package com.example.veb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    protected String korisnickoIme;

    @Column
    protected String lozinka;

    @Column
    protected String ime;

    @Column
    protected String prezime;

    @Enumerated(EnumType.STRING)
    @Column
    protected Pol pol;

    @Temporal(TemporalType.DATE)
    @Column
    protected Date datumRodjenja;

    @Enumerated(EnumType.STRING)
    @Column
    protected Uloga uloga;

    public Korisnik() {
    }

    public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id_korisnika) {
        this.id = id_korisnika;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnicko_ime) {
        this.korisnickoIme = korisnicko_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }


}
