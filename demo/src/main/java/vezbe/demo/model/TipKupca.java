package vezbe.demo.model;

public class TipKupca {

    private String ime;
    private Integer popust;
    private Integer trazeniBodovi;

    public TipKupca() {
    }

    public TipKupca(String ime, Integer popust, Integer trazeniBodovi) {
        this.ime = ime;
        this.popust = popust;
        this.trazeniBodovi = trazeniBodovi;
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

    public Integer getTrazeniBodovi() {
        return trazeniBodovi;
    }

    public void setTrazeniBodovi(Integer trazeniBodovi) {
        this.trazeniBodovi = trazeniBodovi;
    }
}
