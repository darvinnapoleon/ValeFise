package com.example.valefise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpDirectorio extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<String> nomArchivos;
    private List<String> rutArchivos;
    private ArrayAdapter<String> adaptador;
    private String dirRaiz;
    private TextView carActual;
    ListView lisDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_directorio);

        carActual = (TextView) findViewById(R.id.tv_rut);
        lisDir = (ListView) findViewById(R.id.lv_dir);
        dirRaiz = Environment.getExternalStorageDirectory().getPath();
        lisDir.setOnItemClickListener(this);
        verDirectorio(dirRaiz);
    }
    private  void verDirectorio(String rutDirectorio){
        nomArchivos = new ArrayList<String>();
        rutArchivos = new ArrayList<String>();
        int count = 0;
        File dirActual = new File(rutDirectorio);
        File[] lisArchivos = dirActual.listFiles();

        if (!rutDirectorio.equals(dirRaiz)){
            nomArchivos.add("../");
            rutArchivos.add(dirActual.getParent());
            count = 1;
        }

        for (File archivo : lisArchivos){
            rutArchivos.add(archivo.getPath());
        }
        Collections.sort(rutArchivos, String.CASE_INSENSITIVE_ORDER);
        for (int i = count; i<rutArchivos.size(); i++){
            File archivo = new File(rutArchivos.get(i));
            if (archivo.isFile()){
                nomArchivos.add(archivo.getName());
            }else{
                nomArchivos.add("/"+ archivo.getName());
            }
        }
        if (lisArchivos.length < 1){
            nomArchivos.add("No hay ningun archivo");
            rutArchivos.add(rutDirectorio);
        }
        //algo importante
        adaptador = new ArrayAdapter<String>(this,
                R.layout.lista_archivos, nomArchivos);
        lisDir.setAdapter(adaptador);

    }
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        File archivo = new File(rutArchivos.get(i));
        if (archivo.isFile()){}
            Intent is = new Intent(this, LisCliActivity.class);
            is.putExtra("archi", archivo.getAbsolutePath());
            startActivity(is);
        }else{
            verDirectorio(rutArchivos.get(i));
        }
    }

