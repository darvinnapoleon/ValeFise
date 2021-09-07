package com.example.valefise.model;

public class Cli_bidon {
    int idcli;
    String nomcli;
    int idbid;
    int canbid;

    public Cli_bidon(int idcli, String nomcli, int idbid, int canbid) {
        this.idcli = idcli;
        this.nomcli = nomcli;
        this.idbid = idbid;
        this.canbid = canbid;
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

    public int getIdbid() {
        return idbid;
    }

    public void setIdbid(int idbid) {
        this.idbid = idbid;
    }

    public int getCanbid() {
        return canbid;
    }

    public void setCanbid(int canbid) {
        this.canbid = canbid;
    }
}
