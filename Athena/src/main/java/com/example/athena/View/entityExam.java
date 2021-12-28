package com.example.athena.View;



import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class entityExam {
    protected String nome;
    protected int voto;
    protected int CFU;
    protected String data;

    public entityExam(String nome, int voto, int CFU, String  data) {
        this.nome = nome;
        this.voto = voto;
        this.CFU = CFU;
        this.data = data;
    }


}