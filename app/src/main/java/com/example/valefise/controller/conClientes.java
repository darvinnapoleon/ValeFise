package com.example.valefise.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.valefise.R;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.model.Clientes;

import java.util.ArrayList;

public class conClientes extends BaseAdapter{
        ArrayList<Clientes> lista;
        daoClientes dao;
        Clientes c;
        Activity a;
        int id=0;
        validaciones valcam;
        public conClientes(Activity a, ArrayList<Clientes> lista, daoClientes dao) {
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
            valcam = new validaciones();

            //carga los clientes con su nombre y los dos botones
            View v = view;
            final conInflaters coninf = new conInflaters();
            if (v == null){
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.item_cli, null);
            }
            c = lista.get(posicion);
            final TextView nomcli = (TextView) v.findViewById(R.id.tv_nomcli);
            final Button btent = (Button) v.findViewById(R.id.bt_ent);
            final Button btopc = (Button) v.findViewById(R.id.bt_opc);
            nomcli.setText(c.getNomClientes());

            //boton de entregar el gas
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

            //cuando hace click en el boton de opciones
            btopc.setTag(posicion);
            btopc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {

                    //carga una vista de opciones para editar y eliminar
                    int res = R.layout.viewopciones;
                    String tit="";
                    AlertDialog.Builder builder = coninf.retbuilder(res, a);
                    AlertDialog dialog = coninf.retdialog(builder, tit);
                    //boton editar clientes
                    final Button btedi = (Button) dialog.findViewById(R.id.bt_edi);
                    btedi.setTag(view1.getTag());
                    btedi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //cargas el inflater para editar los campos del cliente
                            int res = R.layout.view_cli;
                            String tit="Editar cliente";
                            AlertDialog.Builder builder = coninf.retbuilder(res, a);
                            final AlertDialog dialog = coninf.retdialog(builder, tit);
                            final Button btedi = (Button) dialog.findViewById(R.id.bt_regcli);
                            btedi.setBackgroundResource( R.drawable.ico_edi);
                            final EditText nomcli = (EditText)dialog.findViewById(R.id.et_nomcli);
                            final EditText dnicli = (EditText)dialog.findViewById(R.id.et_dnicli);
                            final int pos = Integer.parseInt(view.getTag().toString());
                            c = lista.get(pos);
                            nomcli.setText(c.getNomClientes());
                            dnicli.setText(c.getDniClientes());
                            //cuando da click en el boton de editar
                            btedi.setTag(view.getTag());
                            btedi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final int pos = Integer.parseInt(view.getTag().toString());
                                    c = lista.get(pos);
                                   int valcam1 = valcam.valcampos(""+nomcli.getText(), "text" );
                                    int valdni = valcam.valcampos(""+dnicli.getText(), "dni" );
                                    if(valcam1 == 0 || valdni == 0){
                                        Toast.makeText(a, "Campos vacios o incompletos", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    if (dao.vercli(dnicli.getText().toString()) == 0  ){
                                        c = new Clientes(c.getIdClientes(), ""+nomcli.getText(), ""+dnicli.getText(), "1");
                                        dao.actcli(c);
                                        lista = dao.liscli("");
                                        notifyDataSetChanged();
                                        dialog.dismiss();
                                        return;
                                    }else{
                                        if (c.getDniClientes().equals(dnicli.getText().toString())){
                                            c = new Clientes(c.getIdClientes(), ""+nomcli.getText(), ""+dnicli.getText(), "1");
                                            dao.actcli(c);
                                            lista = dao.liscli("");
                                            notifyDataSetChanged();
                                            dialog.dismiss();
                                        }else {
                                            Toast.makeText(a, "DNI registrado", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }
                            });
                        }
                    });

                    //boton de eliminar cliente

                    final Button bteli = (Button) dialog.findViewById(R.id.bt_eli);
                    bteli.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(a);
                            builder.setMessage("Estas seguro que deseas eliminar");
                            builder.setCancelable(false);
                            builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    c = new Clientes(getId(), "", "", "0");
                                    dao.elicli(c);
                                    lista=dao.liscli("");
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
                }
            });
            return v;
        }


    }

