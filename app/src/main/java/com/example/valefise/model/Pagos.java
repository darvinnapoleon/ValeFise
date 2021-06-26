package com.example.valefise.model;

public class Pagos {
    int idpag;
    String nomusu;
    String fecpag;

    public Pagos(int idpag, String fecpag) {
        this.idpag = idpag;
        this.fecpag = fecpag;
    }

    public int getIdpag() {
        return idpag;
    }

    public void setIdpag(int idpag) {
        this.idpag = idpag;
    }

    public String getFecpag() {
        return fecpag;
    }

    public void setFecpag(String fecpag) {
        this.fecpag = fecpag;
    }
}
