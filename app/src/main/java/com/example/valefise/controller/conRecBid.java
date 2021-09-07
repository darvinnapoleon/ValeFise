package com.example.valefise.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.valefise.Fragments.RecBid_Fragment;
import com.example.valefise.R;
import com.example.valefise.consults.daoBidones;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.model.Bidones;
import com.example.valefise.model.Cli_bidon;
import com.example.valefise.model.Clientes;

import java.time.LocalDate;
import java.util.ArrayList;

public class conRecBid extends BaseAdapter{
    //declaras las variables que vas a utilizar
    //en su mayoria son ArrayList, modelos, consultas, validaciones y fragmentos
        ArrayList<Cli_bidon> lista;
        daoBidones dao;
        Cli_bidon c;
        Bidones b;
        Activity a;
        int id=0;

        //este controlador siempre lleva su constructor
        // que son un activity, arraylist y un dao y a veces un ListView
        public conRecBid(Activity a, ArrayList<Cli_bidon> lista, daoBidones dao) {
            this.a = a;
            this.lista = lista;
            this.dao = dao;
        }
        //al extenderse de Base Adapter hereda todos estos metodos
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        @Override
        public int getCount() { return lista.size(); }
        @Override
        public Cli_bidon getItem(int i) { c = lista.get(i); return null;
        }
        @Override
        public long getItemId(int i) { c = lista.get(i); return c.getIdbid();
        }
        //en este metodo programas lo que va a cargar
    @Override
        public View getView(final int posicion, View view, ViewGroup viewGroup) {
            //aca cargas el item que vas a mostrar
        // que siempre inicia con item por ejemplo item_bidon
            View v = view;
            final conInflaters coninf = new conInflaters();
            if (v == null){
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.item_recbid, null);
            }
            //inicializas tus variables a utilizar
        // que en su mayoria son validaciones, modelos, consultas y fragmentos
        // poner los datos recuperados, por ejemplo poner los nombres de los clientes
       c = lista.get(posicion);
       final TextView nomcli = (TextView) v.findViewById(R.id.tv_nomcli);
      nomcli.setText(""+c.getNomcli());

        final TextView canbid = (TextView) v.findViewById(R.id.tv_canbid);
       canbid.setText(""+c.getCanbid());

       final Button bt_enc = (Button) v.findViewById(R.id.bt_recbid1);
        bt_enc.setTag(posicion);
        bt_enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                c = lista.get(pos);

                //crear una alerta de confirmacion
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(a);
                builder.setMessage("Seguro que "+c.getNomcli()+" lleva gas");
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     LocalDate fecact= LocalDate.now();
                        int can = c.getCanbid()-1;
                        b = new Bidones(1, c.getIdcli(), can, "", ""+fecact);
                        dao.actbid(b);
                        lista = dao.lisclibid1("");
                        notifyDataSetChanged();
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