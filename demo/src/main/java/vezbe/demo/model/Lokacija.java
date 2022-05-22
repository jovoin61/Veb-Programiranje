package vezbe.demo.model;

public class Lokacija {
    private Double geografskaSirina;
    private Double geografskaDuzina;
    private String adresa;

    public Lokacija() {
    }

    public Lokacija(Double geografskaSirina, Double geografskaDuzina, String adresa) {
        this.geografskaSirina = geografskaSirina;
        this.geografskaDuzina = geografskaDuzina;
        this.adresa = adresa;
    }

    public Double getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(Double geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public Double getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(Double geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
