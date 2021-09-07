package com.example.valefise.model;

public class Cli_Temporal {
    int idClientes;
    String nomClientes;
    String dniClientes;
    String estClientes;
    String codclitemp;

    public Cli_Temporal() {
    }

    public Cli_Temporal(int idClientes, String nomClientes, String dniClientes, String estClientes, String codclitemp) {
        this.idClientes = idClientes;
        this.nomClientes = nomClientes;
        this.dniClientes = dniClientes;
        this.estClientes = estClientes;
        this.codclitemp= codclitemp;
    }

    public int getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(int idClientes) {
        this.idClientes = idClientes;
    }

    public String getNomClientes() {
        return nomClientes;
    }

    public void setNomClientes(String nomClientes) {
        this.nomClientes = nomClientes;
    }

    public String getDniClientes() {
        return dniClientes;
    }

    public void setDniClientes(String dniClientes) {
        this.dniClientes = dniClientes;
    }

    public String getEstClientes() {
        return estClientes;
    }

    public void setEstClientes(String estClientes) {
        this.estClientes = estClientes;
    }

    public String getCodclitemp() {
        return codclitemp;
    }

    public void setCodclitemp(String codclitemp) {
        this.codclitemp = codclitemp;
    }
}
