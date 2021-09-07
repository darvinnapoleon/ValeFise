package com.example.valefise.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.valefise.R;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.controller.conCliBidon;
import com.example.valefise.controller.conClientes;
import com.example.valefise.controller.conFragments;
import com.example.valefise.controller.conInflaters;
import com.example.valefise.controller.validaciones;
import com.example.valefise.model.Clientes;

import java.util.ArrayList;

public class Bid_Fragment extends Fragment  {
    //declaras las variables que vas a utilizar
    //en su mayoria son ArrayList, modelos, consultas, validaciones y fragmentos
    Activity a;
    ArrayList<Clientes> lista;
    daoClientes daocli ;
    conCliBidon conclibid;
    conFragments confragments;
    public Bid_Fragment(Activity a){
        this.a = a;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //cargas el inflater que desees que siempre inicia con
        //la palabra activity por ejemplo activity_bidon
        View view = inflater.inflate(R.layout.activity_bidon, container, false);


        //inicializas tus variables a utilizar
        // que en su mayoria son validaciones, modelos, consultas y fragmentos
        daocli = new daoClientes(a);
        confragments = new conFragments();
        final EditText etnomcli = (EditText) view.findViewById(R.id.et_nomcli);

        //listar cualquier tabla
        final ListView liscli = (ListView) view.findViewById(R.id.lv_cli);
        lista = daocli.liscli("");
        conclibid=new conCliBidon(a,lista,daocli);
        liscli.setAdapter(conclibid);

        //buscar cualquier dato de tu tabla
        final Button btbuscli = (Button) view.findViewById(R.id.bt_buscli);
        btbuscli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista = daocli.liscli(etnomcli.getText().toString());
                conclibid.notifyDataSetChanged();
            }
        });
        //pasar de un fragmento a otro
        final Button btrecbid = (Button) view.findViewById(R.id.bt_recbid);
        btrecbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confragments.ret_Fragment( a, new RecBid_Fragment(a));
            }
        });

        return  view;

    }

}
