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
import com.example.valefise.consults.daoCliTemporales;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.model.Cli_Temporal;
import com.example.valefise.model.Clientes;

import java.util.ArrayList;

public class conconCod extends BaseAdapter{
        ArrayList<Cli_Temporal> lista;
        daoCliTemporales dao;
        Cli_Temporal c;
        Activity a;
        int id=0;
        validaciones valcam;
       public conconCod(Activity a, ArrayList<Cli_Temporal> lista, daoCliTemporales dao) {
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
        public Cli_Temporal getItem(int i) {
            c = lista.get(i);
            return null;
        }

        @Override
        public long getItemId(int i) {
            c = lista.get(i);
            return c.getIdClientes();
        }
        @Override
        public View getView(final int posicion, View view, ViewGroup viewGroup) {
            valcam = new validaciones();

            //carga los clientes con su nombre y los dos botones
            View v = view;
            final conInflaters coninf = new conInflaters();
            if (v == null){
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.itemconcod, null);
            }
            c = lista.get(posicion);
            final TextView nomcli = (TextView) v.findViewById(R.id.tv_nomclitem);
           nomcli.setText(""+c.getNomClientes());
            return v;
        }


    }

