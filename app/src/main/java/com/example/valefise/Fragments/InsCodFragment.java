package com.example.valefise.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.valefise.R;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.controller.conClientes;
import com.example.valefise.controller.conFragments;
import com.example.valefise.controller.conInsCodigo;
import com.example.valefise.controller.validaciones;
import com.example.valefise.model.Clientes;

import java.util.ArrayList;

public class InsCodFragment extends Fragment {
    Activity a;
    public InsCodFragment(Activity a){
        this.a = a;
    }
    daoClientes daocli ;
    validaciones valcam;
    Clientes cli;
    conInsCodigo concli;
    ArrayList<Clientes> lista;
    conFragments confragments;
    @Nullable
    @Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_inscod, container, false);
        //programamos el boton buscar clientes
        //listar cliente
        daocli = new daoClientes(a);
        confragments = new conFragments();
        final ListView liscli = (ListView) view.findViewById(R.id.lv_cli);
        lista = daocli.liscli("");
        concli=new conInsCodigo(a, lista, daocli);
        liscli.setAdapter(concli);

        final Button bt_imp = (Button) view.findViewById(R.id.bt_buscli);
        final EditText nomcli = (EditText) view.findViewById(R.id.et_nomcli);

        bt_imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista = daocli.liscli(nomcli.getText().toString());
                concli.notifyDataSetChanged();

            }
        });
        final Button btvol = (Button) view.findViewById(R.id.bt_concod);
        btvol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confragments.ret_Fragment( a, new ConCod_Fragment(a));
            }
        });
    return  view;
}
}