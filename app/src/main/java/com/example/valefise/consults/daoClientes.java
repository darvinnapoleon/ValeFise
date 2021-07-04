package com.example.valefise.consults;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.valefise.LisCliActivity;
import com.example.valefise.controller.conClientes;
import com.example.valefise.model.Clientes;
import com.example.valefise.model.Pagos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class daoClientes {
    SQLiteDatabase cx;
    ArrayList<Clientes> liscli1 = new ArrayList<Clientes>();
    ArrayList<Integer> listab = new ArrayList<Integer>();
    Clientes cli;
    Context ct;
    String nombreBD = "BDValesFise";
    String tabla = "create table if not exists clientes(idcli integer primary key autoincrement, nomcli text, dnicli text, estcli text)";
    public daoClientes(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }
    //insertar un cliente en la base de datos
    public boolean inscli(Clientes c) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("nomcli", c.getNomClientes());
        contenedor.put("dnicli", c.getDniClientes());
        contenedor.put("estcli", c.getEstClientes());
        return (cx.insert("clientes", null, contenedor)) > 0;
    }

    public ArrayList<Clientes> liscli(String nomcli) {
        liscli1.clear();
        Cursor cursor=cx.rawQuery("select * from clientes where nomcli like '%" + nomcli + "%'", null);
        cursor.moveToLast();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                liscli1.add(new Clientes(
                        cursor.getInt(0),
                        cursor.getString(1) ,
                        cursor.getString(2),
                        cursor.getString(3)));
            }while (cursor.moveToNext());

        }
        return liscli1;
    }

    //verificar si el cliente ya esta registrado y no registrarlo 2 veces
    public int vercli(String dnicli) {
        Cursor cursor=cx.rawQuery("select idcli from clientes where dnicli ="+dnicli, null);
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
    //actualizar clientes
    public boolean actcli(Clientes c){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nomcli", c.getNomClientes());
        contenedor.put("dnicli", c.getDniClientes());
        return (cx.update("clientes", contenedor, "idcli="+c.getIdClientes(), null))>0;
    }
    public boolean elicli(Clientes c){
        ContentValues contenedor = new ContentValues();
        contenedor.put("estcli", c.getEstClientes());
        return (cx.update("clientes", contenedor, "idcli="+c.getIdClientes(), null))>0;
    }


    public  void impcli(Activity a, ListView lvCli){
      ArrayList<Clientes> lisCli;
        conClientes adaptador;
        String archivo = a.getIntent().getStringExtra("archi");
        boolean isCreate = false;
        String cadena;
        String[] arreglo;
        try {
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((cadena = bufferedReader.readLine()) != null){
                arreglo = cadena.split(",");
                if (vercli(""+arreglo[1]) == 0){
                    cli = new Clientes(1, ""+arreglo[0], ""+arreglo[1], ""+arreglo[2]);
                    inscli(cli);
                    lisCli = liscli("");
                    adaptador=new conClientes(a ,lisCli, this);
                    lvCli.setAdapter(adaptador);
                }else{
                    Toast.makeText(a, "el cliente ya existe", Toast.LENGTH_SHORT).show();
                }

            }
        } catch (Exception e) {

        }

    }
    public void formImportacion(){
        File carpeta = new File(Environment.getExternalStorageDirectory() + "/ImpClientes");
        String archivo = carpeta.toString()+"/"+"Clientes.csv";
        boolean isCreate = false;
        if(!carpeta.exists()){
            isCreate= carpeta.mkdir();
        }
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(archivo);
            for(int i=0; i<1; i++) {
                fileWriter.append("Apellidos y Nombres");
                fileWriter.append(",");
                fileWriter.append("Dni");
                fileWriter.append(",");
                fileWriter.append("Estado(0/1)");
                fileWriter.append("\n");
            }
            fileWriter.close();
        }catch (Exception e){ }

    }

    public void expcli(View view, Activity a){
        File carpeta = new File(Environment.getExternalStorageDirectory() + "/Clientes");
        String archivo = carpeta.toString()+"/"+"Clientes.csv";
        boolean isCreate = false;
        if(!carpeta.exists()){
            isCreate= carpeta.mkdir();
        }
        try {

            cli = new Clientes();
            ArrayList<Clientes> lislis = liscli("");
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(archivo);
            for (int i = 0; i < lislis.size(); i++){
                cli = lislis.get(i);
                fileWriter.append(""+cli.getNomClientes());
                fileWriter.append(",");
                fileWriter.append(""+cli.getDniClientes());
                fileWriter.append(",");
                fileWriter.append(""+cli.getEstClientes());
                fileWriter.append("\n");
            }
            fileWriter.close();
            Toast.makeText(a, "exportacion correcta", Toast.LENGTH_SHORT).show();
        }catch (Exception e){ }

    }
}
