package com.example.veb.dto;

import java.util.List;

public class KorpaCenaIspis {

    private List<KorpaIspisDto> korpaIspisDtoList;
    private Double UkupnaCenaKorpe;

    public KorpaCenaIspis() {
    }

    public KorpaCenaIspis(List<KorpaIspisDto> korpaIspisDtoList, Double ukupnaCenaKorpe) {
        this.korpaIspisDtoList = korpaIspisDtoList;
        UkupnaCenaKorpe = ukupnaCenaKorpe;
    }

    public List<KorpaIspisDto> getKorpaIspisDtoList() {
        return korpaIspisDtoList;
    }

    public void setKorpaIspisDtoList(List<KorpaIspisDto> korpaIspisDtoList) {
        this.korpaIspisDtoList = korpaIspisDtoList;
    }

    public Double getUkupnaCenaKorpe() {
        return UkupnaCenaKorpe;
    }

    public void setUkupnaCenaKorpe(Double ukupnaCenaKorpe) {
        UkupnaCenaKorpe = ukupnaCenaKorpe;
    }
}
