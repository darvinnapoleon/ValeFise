package com.example.valefise.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valefise.R;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.consults.daoVales;
import com.example.valefise.controller.conCanVal;
import com.example.valefise.controller.conClientes;
import com.example.valefise.controller.conFragments;

import com.example.valefise.model.Cli_Canjear;


import java.util.ArrayList;

public class CanVal_Fragment extends Fragment {
        public  CanVal_Fragment(Activity a){
            this.a = a;
        }
    daoVales daocli ;
    Cli_Canjear cli;
    conCanVal concli;
    ArrayList<Cli_Canjear> lista;
    Activity a;
    conFragments confragments;
        @Nullable
        @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_canval, container, false);

            //carga los objetos que se usaran
            daocli = new daoVales(a);
            confragments = new conFragments();
            //listar vales para canjear
            //listar cliente
            final ListView liscli = (ListView) view.findViewById(R.id.lv_cli);
            lista = daocli.lisclicanjear("");
            concli=new conCanVal(a, lista, daocli);
            liscli.setAdapter(concli);

            //buscar vales para canjear
            Button buscli = (Button) view.findViewById(R.id.bt_buscli);
            final EditText nomcli = (EditText) view.findViewById(R.id.et_nomcli);
            buscli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lista = daocli.lisclicanjear(nomcli.getText().toString());
                    concli.notifyDataSetChanged();
                }
            });

        return  view;
    }
}