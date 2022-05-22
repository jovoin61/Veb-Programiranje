package vezbe.demo.model;

import java.util.HashSet;
import java.util.Set;

public class Kupac extends Korisnik {
    private Set<Porudzbina> porudzbine = new HashSet<>();
    private Integer bodovi;
    private TipKupca tipKupca;

    public Kupac(Set<Porudzbina> porudzbine, Integer bodovi, TipKupca tipKupca) {
        this.setUloga(Uloga.KUPAC);
    }

    public Kupac(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Set<Porudzbina> porudzbine, Integer bodovi, TipKupca tipKupca) {
        super(korisnicko_ime, lozinka, ime, prezime, pol);
        this.bodovi = bodovi;
        this.tipKupca = tipKupca;
        this.setUloga(Uloga.KUPAC);
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public Integer getBodovi() {
        return bodovi;
    }

    public void setBodovi(Integer bodovi) {
        this.bodovi = bodovi;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }
}
