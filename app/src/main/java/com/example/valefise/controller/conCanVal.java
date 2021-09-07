package com.example.valefise.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.valefise.R;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.consults.daoVales;
import com.example.valefise.model.Cli_Canjear;
import com.example.valefise.model.Clientes;

import java.util.ArrayList;

public class conCanVal extends BaseAdapter{
        ArrayList<Cli_Canjear> lista;
        daoVales dao;
        Cli_Canjear c;
        Activity a;
        int id=0;
        validaciones valcam;
        public conCanVal(Activity a, ArrayList<Cli_Canjear> lista, daoVales dao) {
            this.a = a;
            this.lista = lista;
            this.dao = dao;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int getCount() {
            return lista.size();
        }

        @Override
        public Cli_Canjear getItem(int i) {
            c = lista.get(i);
            return null;
        }

        @Override
        public long getItemId(int i) {
            c = lista.get(i);
            return c.getIdcli();
        }
        @Override
        public View getView(final int posicion, View view, ViewGroup viewGroup) {
            valcam = new validaciones();

            //carga los clientes con su nombre y los dos botones
            View v = view;
            final conInflaters coninf = new conInflaters();
            if (v == null){
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.itemcanval, null);
            }
            c = lista.get(posicion);
            final TextView nomcli = (TextView) v.findViewById(R.id.et_nomcli);
            nomcli.setText(c.getNomcli());
                    //boton de canjear vale

                    final Button bteli = (Button) v.findViewById(R.id.bt_canval);
                    bteli.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(a);
                            builder.setMessage("Seguro que vas a canjear vale de"+c.getNomcli());
                            builder.setCancelable(false);
                            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();
                        }
                    });

            return v;
        }


    }

