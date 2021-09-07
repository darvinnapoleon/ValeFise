package com.example.valefise.consults;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.valefise.model.Cli_Canjear;
import com.example.valefise.model.Cli_Temporal;
import com.example.valefise.model.Clientes;
import com.example.valefise.model.Vales;

import java.util.ArrayList;

public class daoCliTemporales {

    ArrayList<Cli_Temporal> lisclitemp = new ArrayList<Cli_Temporal>();

    SQLiteDatabase cx;
    Context ct;
    String nombreBD = "BDValesFise";
    String tabla = "create table if not exists clitemporal(idcli integer primary key autoincrement, nomcli text, dnicli text, estcli text, codcli text)";
    public daoCliTemporales(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }

    //cliente temporal, pasar un array tabla vales, declarar nombre lisdni
    public ArrayList<Cli_Temporal> lisclitemp() {
        lisclitemp.clear();
        Cursor cursor=cx.rawQuery("select * from clientes", null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                lisclitemp.add(new Cli_Temporal(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        ""));
            }while (cursor.moveToNext());

        }
        return lisclitemp;
    }

    public ArrayList<Cli_Temporal> lisclitemp1() {
        lisclitemp.clear();
        Cursor cursor=cx.rawQuery("select * from clitemporal", null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                lisclitemp.add(new Cli_Temporal(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            }while (cursor.moveToNext());

        }
        return lisclitemp;
    }
    //lista vale
    public ArrayList<Cli_Temporal> lisclitempval() {
        lisclitemp.clear();
        Cursor cursor=cx.rawQuery("select * from clitemp where estcli='1'", null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                lisclitemp.add(new Cli_Temporal(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            }while (cursor.moveToNext());

        }
        return lisclitemp;
    }
    //insertar vales
    public boolean insclitem(Cli_Temporal c) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("idcli", c.getIdClientes());
        contenedor.put("nomcli", c.getNomClientes());
        contenedor.put("dnicli", c.getDniClientes());
        contenedor.put("estcli", c.getEstClientes());
        contenedor.put("codcli", c.getCodclitemp());
        return (cx.insert("clitemporal", null, contenedor)) > 0;
    }

    //actualizar el campo feccan y estval
    public boolean actclitemp(Cli_Temporal c){
        ContentValues contenedor = new ContentValues();
        contenedor.put("estcli", c.getEstClientes());
        contenedor.put("codcli", c.getCodclitemp());
        return (cx.update("clitemporal", contenedor, "idcli="+c.getIdClientes(), null))>0;
    }

    public boolean eliminar(String dni) {

        return (cx.delete("clitemporal", "idcli="+dni, null))>0;
    }
}
