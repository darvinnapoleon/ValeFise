package com.example.valefise.controller;

public class validaciones {

    public int valcampos(String texto, String tipo){
       switch (tipo){
           case "text" :
               return valtext(texto);
           case "dni" :
               return valdni(texto);
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
}
