package com.example.athena.entities;


public class EntityExam {
    protected String nome;
    protected int voto;
    protected int cfu;
    protected String data;
    

    public EntityExam(String nome, int voto, int cfu, String  data) {
        this.nome = nome;
        this.voto = voto;
        this.cfu = cfu;
        this.data = data;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public int getVoto() {
        return voto;
    }

    public String getNome() {
        return nome;
    }

    public int getCfu() {
        return cfu;
    }

    public String getData() {
        return data;
    }
    
}