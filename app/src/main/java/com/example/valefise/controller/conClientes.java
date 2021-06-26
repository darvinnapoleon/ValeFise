package com.example.valefise.controller;

import android.app.Activity;
import android.content.Context;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.valefise.LisCliActivity;
import com.example.valefise.R;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.model.Clientes;

import java.util.ArrayList;

public class conClientes extends BaseAdapter{
        ArrayList<Clientes> lista;
        ListView rvCli;
        daoClientes dao;
        Clientes c;
        Activity a;
        int id=0;
        String tel;
        public conClientes(ArrayList<Clientes> lista, daoClientes dao, Activity a) {
            this.lista = lista;
            this.dao = dao;
            this.a = a;
            this.rvCli = rvCli;
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
        public Clientes getItem(int i) {
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
            View v = view;
            final conInflaters coninf = new conInflaters();
            if (v == null){
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.item_cli, null);
            }
            c = lista.get(posicion);
            final TextView nomcli = (TextView) v.findViewById(R.id.et_nomcli);
            final Button btent = (Button) v.findViewById(R.id.bt_ent);
            final Button btopc = (Button) v.findViewById(R.id.bt_opc);
            btent.setTag(posicion);
            btent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int res = R.layout.viewentgas;
                    String tit="Entrega de gas";
                    AlertDialog.Builder builder = coninf.retbuilder(res, a);
                    AlertDialog dialog = coninf.retdialog(builder, tit);
                    final EditText nomcli = (EditText)dialog.findViewById(R.id.et_nomcli);
                }
            });
            btopc.setTag(posicion);
            btopc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return v;
        }


    }

