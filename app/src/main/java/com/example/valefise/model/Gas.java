package com.example.valefise.model;

public class Gas {
    int idgas;
    String nomgas;
    int stogas;
    double presugventas0;
    double presugventas1;
    double presugventas2;
    double presugventas3;

    public Gas() {
    }

    public Gas(int idgas, String nomgas, int stogas, double presugventas0, double presugventas1, double presugventas2, double presugventas3) {
        this.idgas = idgas;
        this.nomgas = nomgas;
        this.stogas = stogas;
        this.presugventas0 = presugventas0;
        this.presugventas1 = presugventas1;
        this.presugventas2 = presugventas2;
        this.presugventas3 = presugventas3;
    }

    public int getIdgas() {
        return idgas;
    }

    public void setIdgas(int idgas) {
        this.idgas = idgas;
    }

    public String getNomgas() {
        return nomgas;
    }

    public void setNomgas(String nomgas) {
        this.nomgas = nomgas;
    }

    public int getStogas() {
        return stogas;
    }

    public void setStogas(int stogas) {
        this.stogas = stogas;
    }

    public double getPresugventas0() {
        return presugventas0;
    }

    public void setPresugventas0(double presugventas0) {
        this.presugventas0 = presugventas0;
    }

    public double getPresugventas1() {
        return presugventas1;
    }

    public void setPresugventas1(double presugventas1) {
        this.presugventas1 = presugventas1;
    }

    public double getPresugventas2() {
        return presugventas2;
    }

    public void setPresugventas2(double presugventas2) {
        this.presugventas2 = presugventas2;
    }

    public double getPresugventas3() {
        return presugventas3;
    }

    public void setPresugventas3(double presugventas3) {
        this.presugventas3 = presugventas3;
    }
}
