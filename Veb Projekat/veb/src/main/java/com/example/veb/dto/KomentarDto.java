package com.example.veb.dto;

import com.example.veb.model.Komentar;

public class KomentarDto {
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String tekst;
    private Integer ocena;

    public KomentarDto() {
    }

    public KomentarDto(Komentar k) {
        this.korisnickoIme = k.getKupac().getKorisnickoIme();
        this.ime = k.getKupac().getIme();
        this.prezime = k.getKupac().getPrezime();
        this.tekst = k.getTekst_komentara();
        this.ocena = k.getOcena();
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
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

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }
}
