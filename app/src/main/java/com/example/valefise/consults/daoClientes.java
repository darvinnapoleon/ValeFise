package com.example.valefise.consults;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.example.valefise.controller.conClientes;
import com.example.valefise.model.Clientes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class daoClientes {
    SQLiteDatabase cx;
    Context ct;
    String nombreBD = "BDValesFise";
    String tabla = "create table if not exists clientes(idcli integer primary key autoincrement, nomcli text, dnicli text, estcli text)";
    public daoClientes(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }
    ArrayList<Clientes> liscli1 = new ArrayList<Clientes>();
    ArrayList<Integer> listab = new ArrayList<Integer>();
    Clientes cli;
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
    public  void impcli(Activity a, ListView lvCli, String archivo){
      ArrayList<Clientes> lisCli;
        conClientes adaptador;
        boolean isCreate = false;
        String cadena;
        String[] arreglo;
        try {
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((cadena = bufferedReader.readLine()) != null){
                arreglo = cadena.split(";");
                if (arreglo[1].length()!= 8){
                    Toast.makeText(a, "DNI errados", Toast.LENGTH_SHORT).show();
                }else{
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
            }
        } catch (Exception e) {

        }

    }
    public void forImportacion1(){
        Workbook wb=new HSSFWorkbook();
        Cell cell=null;
        CellStyle cellStyle=wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        Sheet sheet=null;
        sheet = wb.createSheet("Name of sheet");
        //Now column and row
        Row row =sheet.createRow(0);
        cell=row.createCell(0);
        cell.setCellValue("Apellidos y nombres");
        cell.setCellStyle(cellStyle);
        cell=row.createCell(1);
        cell.setCellValue("DNI");
        cell.setCellStyle(cellStyle);
        cell=row.createCell(2);
        cell.setCellValue("Estado(0/1)");
        cell.setCellStyle(cellStyle);
        sheet.setColumnWidth(0,(10*600));
        sheet.setColumnWidth(1,(10*350));
        sheet.setColumnWidth(2,(10*350));
        File file = new File(Environment.getExternalStorageDirectory() + "/ImpClientes","Clientes.xls");
        FileOutputStream outputStream =null;
        try {
            outputStream = new FileOutputStream(file);
            wb.write(outputStream);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
