package com.example.valefise.consults;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.valefise.model.Bidones;

import com.example.valefise.model.Cli_bidon;
import java.util.ArrayList;

public class daoBidones{
    //creas cualquier tabla en tu base de datos
    //SQLite acepta solo integer y text.
    SQLiteDatabase cx;
    Context ct;
    String nombreBD = "BDValesFise";
    String tabla = "create table if not exists bidones(idbid integer primary key autoincrement, idcli integer, canbid integer, fecenc text,fecrec text)";
    public daoBidones(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }

    //declaras tus variable que vas a utilizar
    //que en la mayoria son ArrayList y modelos
    ArrayList<Cli_bidon> lisclibid = new ArrayList<Cli_bidon>();
    Bidones bid;

    //asi puedes insertar datos a cualquier tabla de tu base de datos
    public boolean insbidon(Bidones c) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("idcli", c.getIdcli());
        contenedor.put("canbid", c.getCanbid());
        contenedor.put("fecenc", c.getFecenc());
        contenedor.put("fecrec", c.getFecrec());
        return (cx.insert("bidones", null, contenedor)) > 0;
    }


    //asi puedes verificar si un dato ya existe en tu base de datos
    //por ejemplo si un cliente ya esta registrado y asi evitar registrarlo 2 veces
    public int vercli(int idcli) {
        Cursor cursor=cx.rawQuery("select idbid from bidones where idcli ="+idcli, null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            int val;
            cursor.moveToFirst();
            do {
               val=  cursor.getInt(0);
            }while (cursor.moveToNext());
            return val;
        }
        return 0;
    }

    //con esto puedes actualizar cualquier dato de tus tablas
    public boolean actbid(Bidones c){
        ContentValues contenedorb = new ContentValues();
        contenedorb.put("canbid",c.getCanbid());
        contenedorb.put("fecenc", c.getFecenc());
        contenedorb.put("fecrec", c.getFecrec());
        return (cx.update("bidones", contenedorb, "idcli="+c.getIdcli(), null))>0;
    }

    //lcon esto puedes listar cualquier tabla de tu base de datos

    public ArrayList<Cli_bidon> lisclibid1(String nomcli) {
        lisclibid.clear();
        Cursor cursor=cx.rawQuery("select c.idcli, c.nomcli,  b.idbid, b.canbid from clientes as c INNER JOIN bidones as b ON c.idcli = b.idcli where b.canbid>0  and c.nomcli like '%" + nomcli + "%'", null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                lisclibid.add(new Cli_bidon(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3)));
            }while (cursor.moveToNext());

        }
        return lisclibid;
    }

    //ver cantidad de bidones
    public int canbid(int idcli) {
        Cursor cursor=cx.rawQuery("select canbid from bidones where idcli ="+idcli, null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            int val;
            cursor.moveToFirst();
            do {
                val=  cursor.getInt(0);
            }while (cursor.moveToNext());
            return val;
        }
        return 0;
    }
}
