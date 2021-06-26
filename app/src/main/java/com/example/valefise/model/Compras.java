package com.example.valefise.model;

public class Compras {
    int idcompras;
    String feccompras;
    double totcompras;

    public Compras() {
    }

    public Compras(int idcompras, String feccompras, double totcompras) {
        this.idcompras = idcompras;
        this.feccompras = feccompras;
        this.totcompras = totcompras;
    }

    public int getIdcompras() {
        return idcompras;
    }

    public void setIdcompras(int idcompras) {
        this.idcompras = idcompras;
    }

    public String getFeccompras() {
        return feccompras;
    }

    public void setFeccompras(String feccompras) {
        this.feccompras = feccompras;
    }

    public double getTotcompras() {
        return totcompras;
    }

    public void setTotcompras(double totcompras) {
        this.totcompras = totcompras;
    }
}
