package vezbe.demo.model;


import javax.persistence.*;
import javax.swing.plaf.multi.MultiSliderUI;
import java.io.Serializable;

public class Korisnik implements Serializable {

    private String korisnicko_ime;

    private String lozinka;
    private String ime;
    private String prezime;

    enum Pol{
        MUSKI,
        ZENSKI
    }

    private Pol pol;

    //TODO DATUM DATUM DATUM!!!!!!!!!

    enum Uloga{
        ADMIN,
        MENADZER,
        DOSTAVLJAC,
        KUPAC
    }

    private Uloga uloga = null;

    public Korisnik() {
    }

    public Korisnik(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol) {
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public Uloga getUloga() {
        return uloga;
    }
}
