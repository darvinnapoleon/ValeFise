package com.example.valefise.model;

public class Vales {
    int idval;
    String dnicli;
    String codcli;
    String responsable;
    String feccan;
    String fecrec;
    int estval;
    public Vales(String dnicli) {
        this.dnicli = dnicli;
    }
    public Vales(int idval, String dnicli, String codcli, String responsable, String feccan, String fecrec, int estval) {
        this.idval = idval;
        this.dnicli = dnicli;
        this.codcli = codcli;
        this.responsable = responsable;
        this.feccan = feccan;
        this.fecrec = fecrec;
        this.estval = estval;
    }

    public int getIdval() {
        return idval;
    }

    public void setIdval(int idval) {
        this.idval = idval;
    }

    public String getDnicli() {
        return dnicli;
    }

    public void setDnicli(String dnicli) {
        this.dnicli = dnicli;
    }

    public String getCodcli() {
        return codcli;
    }

    public void setCodcli(String codcli) {
        this.codcli = codcli;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getFeccan() {
        return feccan;
    }

    public void setFeccan(String feccan) {
        this.feccan = feccan;
    }

    public String getFecrec() {
        return fecrec;
    }

    public void setFecrec(String fecrec) {
        this.fecrec = fecrec;
    }

    public int getEstval() {
        return estval;
    }

    public void setEstval(int estval) {
        this.estval = estval;
    }
}

