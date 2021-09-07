package com.example.valefise.model;

public class Bidones {
    //asi de esta manera puedes crear cualquier modelo
    //que tiene variables, constructor y get y set
    int idbid;
    int idcli;
    int canbid;
    String fecenc;
    String fecrec;

    public Bidones() {
    }

    public Bidones(int idbid, int idcli, int canbid, String fecenc, String fecrec) {
        this.idbid = idbid;
        this.idcli = idcli;
        this.canbid = canbid;
        this.fecenc = fecenc;
        this.fecrec = fecrec;
    }

    public int getIdbid() {
        return idbid;
    }

    public void setIdbid(int idbid) {
        this.idbid = idbid;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public int getCanbid() {
        return canbid;
    }

    public void setCanbid(int canbid) {
        this.canbid = canbid;
    }

    public String getFecenc() {
        return fecenc;
    }

    public void setFecenc(String fecenc) {
        this.fecenc = fecenc;
    }

    public String getFecrec() {
        return fecrec;
    }

    public void setFecrec(String fecrec) {
        this.fecrec = fecrec;
    }
}
