package com.example.veb.dto;

public class KomentarNoviDto {

    private String komentar;
    private Integer ocena;

    public KomentarNoviDto() {
    }

    public KomentarNoviDto(String komentar, Integer ocena) {
        this.komentar = komentar;
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }
}
