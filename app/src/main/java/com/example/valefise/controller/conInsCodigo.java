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

import com.example.valefise.Fragments.ConCod_Fragment;
import com.example.valefise.Fragments.InsCodFragment;
import com.example.valefise.R;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.consults.daoVales;
import com.example.valefise.model.Clientes;
import com.example.valefise.model.Vales;

import java.util.ArrayList;

public class conInsCodigo extends BaseAdapter{
        ArrayList<Clientes> lista;
        daoClientes dao;
        daoVales daoval;
        Clientes c;
        Vales vales;
        Activity a;
        int id=0;
        validaciones valcam;
        conFragments confragments;
        public conInsCodigo(Activity a, ArrayList<Clientes> lista, daoClientes dao) {
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
            daoval = new daoVales(a);
            confragments = new conFragments();
            //carga los clientes con su nombre y los dos botones
            View v = view;
            final conInflaters coninf = new conInflaters();
            if (v == null){
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.itemcanval, null);
            }
            c = lista.get(posicion);
            final TextView nomcli = (TextView) v.findViewById(R.id.et_nomcli);
            final Button btopc = (Button) v.findViewById(R.id.bt_canval);
            nomcli.setText(c.getNomClientes());
            btopc.setTag(posicion);
            btopc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    final int pos = Integer.parseInt(view1.getTag().toString());
                    c = lista.get(pos);
                    //carga una vista de opciones para editar y eliminar
                    int res = R.layout.view_inscod;
                    String tit = ""+c.getNomClientes();
                    AlertDialog.Builder builder = coninf.retbuilder(res, a);
                    final AlertDialog dialog = coninf.retdialog(builder, tit);
                    final Button bt_guacod = (Button) dialog.findViewById(R.id.bt_regcod);
                    final EditText et_codcli = (EditText) dialog.findViewById(R.id.et_codcli);
                    bt_guacod.setTag(view1.getTag());
                    bt_guacod.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final int pos = Integer.parseInt(view.getTag().toString());
                            c = lista.get(pos);
                            String codcli = et_codcli.getText().toString();
                            int valcod = valcam.valcampos(codcli, "codval");
                            if(valcod == 0){
                                Toast.makeText(a, "Codigo incorrecto", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (daoval.vercodcli(codcli) == 0) {
                                    vales = new Vales(0,c.getDniClientes(),codcli ,"","","",0);
                                    daoval.insval(vales);
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(a, "el codigo ya existe", Toast.LENGTH_SHORT).show();
                                }


                        }
                    });
                }
                });


            return v;
        }
    }

