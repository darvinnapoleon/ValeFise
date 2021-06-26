package com.example.valefise.model;

public class Det_Ventas {
    int iddetcompras;
    int idgas;
    int idcompras;
    int cancompras;
    double precompras;
    double subcompras;

    public Det_Ventas() {
    }

    public Det_Ventas(int iddetcompras, int idgas, int idcompras, int cancompras, double precompras, double subcompras) {
        this.iddetcompras = iddetcompras;
        this.idgas = idgas;
        this.idcompras = idcompras;
        this.cancompras = cancompras;
        this.precompras = precompras;
        this.subcompras = subcompras;
    }

    public int getIddetcompras() {
        return iddetcompras;
    }

    public void setIddetcompras(int iddetcompras) {
        this.iddetcompras = iddetcompras;
    }

    public int getIdgas() {
        return idgas;
    }

    public void setIdgas(int idgas) {
        this.idgas = idgas;
    }

    public int getIdcompras() {
        return idcompras;
    }

    public void setIdcompras(int idcompras) {
        this.idcompras = idcompras;
    }

    public int getCancompras() {
        return cancompras;
    }

    public void setCancompras(int cancompras) {
        this.cancompras = cancompras;
    }

    public double getPrecompras() {
        return precompras;
    }

    public void setPrecompras(double precompras) {
        this.precompras = precompras;
    }

    public double getSubcompras() {
        return subcompras;
    }

    public void setSubcompras(double subcompras) {
        this.subcompras = subcompras;
    }
}
