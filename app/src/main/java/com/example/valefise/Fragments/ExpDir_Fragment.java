package com.example.valefise.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.valefise.R;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.controller.conFragments;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpDir_Fragment extends Fragment implements AdapterView.OnItemClickListener{
    private List<String> nomArchivos;
    private List<String> rutArchivos;
    private ArrayAdapter<String> adaptador;
    private String dirRaiz;
    private TextView carActual;
    ListView lisDir;
    daoClientes daocli;
    Activity a;
    conFragments confragments;
    Cli_Fragment cli_fragment;
    public ExpDir_Fragment(Activity a){
        this.a=a;
    }
    @Nullable
    @Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exp_directorio, container, false);
        carActual = (TextView) view.findViewById(R.id.tv_rut);
        lisDir = (ListView) view.findViewById(R.id.lv_dir);
        dirRaiz = Environment.getExternalStorageDirectory().getPath();
        lisDir.setOnItemClickListener(ExpDir_Fragment.this);
        verDirectorio(dirRaiz);
        daocli = new daoClientes(a);
    return  view;
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
        adaptador = new ArrayAdapter<String>(a,
                R.layout.lista_archivos, nomArchivos);
        lisDir.setAdapter(adaptador);
    }


    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        File archivo = new File(rutArchivos.get(i));
        if (archivo.isFile()){
            cli_fragment = new Cli_Fragment(a);
            Bundle bundle = new Bundle();
            bundle.putString("directorio",archivo.getAbsolutePath());
            cli_fragment.setArguments(bundle);
            confragments = new conFragments();
            confragments.ret_Fragment( a, cli_fragment);
        }else{
            verDirectorio(rutArchivos.get(i));
        }
    }

}