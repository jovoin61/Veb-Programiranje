package vezbe.demo.model;

public class Menadzer extends Korisnik {

    private Restoran restoran;

    public Menadzer(Restoran restoran) {
        this.restoran = restoran;
        this.setUloga(Uloga.MENADZER);
    }

    public Menadzer(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Uloga uloga, Restoran restoran) {
        super(korisnicko_ime, lozinka, ime, prezime, pol);
        this.restoran = restoran;
        this.setUloga(Uloga.MENADZER);
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }
}
