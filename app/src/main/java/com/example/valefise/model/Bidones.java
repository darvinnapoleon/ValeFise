package com.example.valefise.model;

public class Bidones {
    int idbidones;
    int cantbidones;
    int idclientes;
    String fecencargo;
    String fecreclamo;

    public Bidones() {
    }

    public Bidones(int idbidones, int cantbidones, int idclientes, String fecencargo, String fecreclamo) {
        this.idbidones = idbidones;
        this.cantbidones = cantbidones;
        this.idclientes = idclientes;
        this.fecencargo = fecencargo;
        this.fecreclamo = fecreclamo;
    }

    public int getIdbidones() {
        return idbidones;
    }

    public void setIdbidones(int idbidones) {
        this.idbidones = idbidones;
    }

    public int getCantbidones() {
        return cantbidones;
    }

    public void setCantbidones(int cantbidones) {
        this.cantbidones = cantbidones;
    }

    public int getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(int idclientes) {
        this.idclientes = idclientes;
    }

    public String getFecencargo() {
        return fecencargo;
    }

    public void setFecencargo(String fecencargo) {
        this.fecencargo = fecencargo;
    }

    public String getFecreclamo() {
        return fecreclamo;
    }

    public void setFecreclamo(String fecreclamo) {
        this.fecreclamo = fecreclamo;
    }
}
