package com.example.athena;



import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class entityExam {
    protected String nome;
    protected int voto;
    protected int CFU;
    protected LocalDate data;

    public entityExam(String nome, int voto, int CFU, LocalDate data) {
        this.nome = nome;
        this.voto = voto;
        this.CFU = CFU;
        this.data = data;
    }


}