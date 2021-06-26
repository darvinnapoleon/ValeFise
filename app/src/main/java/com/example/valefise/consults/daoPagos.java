package com.example.valefise.consults;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.valefise.model.Pagos;

import java.util.ArrayList;

public class daoPagos {
    SQLiteDatabase cx;
    ArrayList<Pagos> lispag = new ArrayList<Pagos>();
    ArrayList<Integer> listab = new ArrayList<Integer>();
    Pagos pag;
    Context ct;
    String nombreBD = "BDValesFise";
    String tabla = "create table if not exists pagos(idpag integer primary key autoincrement, fecpag text)";
    public daoPagos(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }
    public boolean inspag(Pagos p) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("fecpag", p.getFecpag());
        return (cx.insert("pagos", null, contenedor)) > 0;
    }

    public ArrayList<Pagos> verPagos() {
        lispag.clear();
        Cursor cursor=cx.rawQuery("select * from pagos", null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                lispag.add(new Pagos(cursor.getInt(0),
                        cursor.getString(1) ));
            }while (cursor.moveToNext());

        }
        return lispag;
    }
    public ArrayList<Integer> verTabla() {
        listab.clear();
        Cursor cursor=cx.rawQuery("select count(*) from pagos", null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                listab.add(cursor.getInt(0) );
            }while (cursor.moveToNext());

        }
        return listab;
    }
    public boolean actpag(Pagos p){
        ContentValues conpag = new ContentValues();
        conpag.put("fecpag", p.getFecpag());
        return (cx.update("pagos", conpag, "idpag="+p.getIdpag(), null))>0;
    }
}
