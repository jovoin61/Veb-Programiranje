package vezbe.demo.model;

import java.util.HashSet;
import java.util.Set;

public class Dostavljac extends Korisnik {
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac(String restoran) {
        this.setUloga(Uloga.DOSTAVLJAC);
    }

    public Dostavljac(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Uloga uloga, String restoran) {
        super(korisnicko_ime, lozinka, ime, prezime, pol);
        this.setUloga(Uloga.DOSTAVLJAC);
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }
}
