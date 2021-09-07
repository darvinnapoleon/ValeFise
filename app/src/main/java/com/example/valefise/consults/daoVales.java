package com.example.valefise.consults;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.ListView;
import android.widget.Toast;

import com.example.valefise.controller.conClientes;
import com.example.valefise.model.Cli_Canjear;
import com.example.valefise.model.Cli_Temporal;
import com.example.valefise.model.Clientes;
import com.example.valefise.model.Vales;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class daoVales {
    ArrayList<Clientes> liscli1 = new ArrayList<Clientes>();
    ArrayList<Cli_Canjear> lisclivales = new ArrayList<Cli_Canjear>();
    ArrayList<Vales> lisdnival=new ArrayList<Vales>();
    ArrayList<Cli_Temporal> lisclitemp = new ArrayList<Cli_Temporal>();
    SQLiteDatabase cx;
    Context ct;
    String nombreBD = "BDValesFise";
    String tabla = "create table if not exists vales(idval integer primary key autoincrement, dnicli text, codcli text, responsable text, feccan text, fecrec text, estval integer)";
    public daoVales(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }
    //listar los dni de la tabla vale where fecval = fecha enviada
    public ArrayList<Vales> lisdnival(String codcliente) {
        lisdnival.clear();
        Cursor cursor=cx.rawQuery("select dnicli from vales where codcli like '"+codcliente+"%'", null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                lisdnival.add(new Vales(
                        cursor.getString(0)));
            }while (cursor.moveToNext());

        }
        return lisdnival;
    }
    //cliente temporal, pasar un array tabla vales, declarar nombre lisdni

    //insertar vales
    public boolean insval(Vales c) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("dnicli", c.getDnicli());
        contenedor.put("codcli", c.getCodcli());
        contenedor.put("responsable", c.getResponsable());
        contenedor.put("feccan", c.getFeccan());
        contenedor.put("fecrec", c.getFecrec());
        contenedor.put("estval", c.getEstval());
        return (cx.insert("vales", null, contenedor)) > 0;
    }

    //verificar si hay codigos de vale
    public int vercodcli(String codclia) {
        Cursor cursor=cx.rawQuery("select idval from vales where codcli = '"+codclia+"'" , null);
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
    //actualizar el campo feccan y estval
    public boolean actcli(Clientes c){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nomcli", c.getNomClientes());
        contenedor.put("dnicli", c.getDniClientes());
        return (cx.update("clientes", contenedor, "idcli="+c.getIdClientes(), null))>0;
    }

    // actualizar el campo responsable y fecrec y estvald
    public boolean actcli1(Clientes c){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nomcli", c.getNomClientes());
        contenedor.put("dnicli", c.getDniClientes());
        return (cx.update("clientes", contenedor, "idcli="+c.getIdClientes(), null))>0;
    }

    public ArrayList<Cli_Canjear> lisclicanjear(String nomcli) {
        lisclivales.clear();
        Cursor cursor=cx.rawQuery("select c.idcli, c.nomcli, c.dnicli, v.codcli from clientes as  c" +
                " INNER JOIN vales as v ON c.dnicli = v.dnicli where v.estval = 0 and c.nomcli like '%" + nomcli + "%'", null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                lisclivales.add(new Cli_Canjear(
                        cursor.getInt(0),
                        cursor.getString(1) ,
                        cursor.getString(2),
                        cursor.getString(3)));
            }while (cursor.moveToNext());

        }
        return lisclivales;
    }
}
