package com.example.valefise.model;

public class Ventas {
    int idventas;
    double totventas;
    String fecventas;

    public Ventas() {
    }

    public Ventas(int idventas, double totventas, String fecventas) {
        this.idventas = idventas;
        this.totventas = totventas;
        this.fecventas = fecventas;
    }

    public int getIdventas() {
        return idventas;
    }

    public void setIdventas(int idventas) {
        this.idventas = idventas;
    }

    public double getTotventas() {
        return totventas;
    }

    public void setTotventas(double totventas) {
        this.totventas = totventas;
    }

    public String getFecventas() {
        return fecventas;
    }

    public void setFecventas(String fecventas) {
        this.fecventas = fecventas;
    }
}
