package com.example.valefise.model;

public class Cli_Canjear {
    int idcli;
    String nomcli;
    String dnicli;
    String codval;

    public Cli_Canjear() {
    }

    public Cli_Canjear(int idcli, String nomcli, String dnicli, String codval) {
        this.idcli = idcli;
        this.nomcli = nomcli;
        this.dnicli = dnicli;
        this.codval = codval;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public String getNomcli() {
        return nomcli;
    }

    public void setNomcli(String nomcli) {
        this.nomcli = nomcli;
    }

    public String getDnicli() {
        return dnicli;
    }

    public void setDnicli(String dnicli) {
        this.dnicli = dnicli;
    }

    public String getCodval() {
        return codval;
    }

    public void setCodval(String codval) {
        this.codval = codval;
    }

}
