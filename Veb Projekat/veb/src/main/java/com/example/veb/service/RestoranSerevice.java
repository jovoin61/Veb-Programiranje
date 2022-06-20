package com.example.veb.service;


import com.example.veb.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestoranSerevice {

    @Autowired
    private RestoranRepository restoranRepository;
}
