package com.example.valefise.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class validaciones {

    public int valcampos(String texto, String tipo){
       switch (tipo){
           case "text" :
               return valtext(texto);
           case "dni" :
               return valdni(texto);
           case "codval" :
               return valcodval(texto);
           case "telf" :
               return valtef(texto);
           default:
               return 0;
       }
    }
    public int valtext(String texto){
        if(texto.length()<=0){
            return 0;
        }
        return 1;
    }
    public int valdni(String texto){
        if(texto.length()!=8){
            return 0;
        }
        return 1;
    }
    public int valcodval(String texto){
        if(texto.length()!=13 || Integer.parseInt(texto.substring(2,4))>12 ||
                Integer.parseInt(texto.substring(0,2)) != 6){
            return 0;
        }
        return 1;
    }
    public int valtef(String texto){
        if(texto.length()!=9 ){
            return 0;
        }
        return 1;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String obtfechas(String formatofecha){
        LocalDate fechaactual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatofecha);
        String fechaactual1 = fechaactual.format(formatter);
        return fechaactual1;
    }
    public String validarmes(int mes){
        String mesvalidado;
        if (mes<10){
           return mesvalidado = "0"+mes;
        }else{
            return mesvalidado = ""+mes;
        }
    }
    public int valcodigovale(String fechaactual){
        if (Integer.parseInt(fechaactual.substring(0,2))<=15){
            return Integer.parseInt(fechaactual.substring(2,4))- 2;
        }else{
            return Integer.parseInt(fechaactual.substring(2,4))- 1;
        }
    }
}
