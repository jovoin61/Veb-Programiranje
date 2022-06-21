package com.example.veb.dto;

import com.example.veb.model.Kupac;
import com.example.veb.model.Pol;

import java.util.Date;

public class RegistracijaDto {

    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private Pol pol;
    private Date datumRodjenja;

    public RegistracijaDto() {
    }

    public RegistracijaDto(String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
    }

    public Kupac konvertuj_u_kupca() {
        return new Kupac(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
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
}
