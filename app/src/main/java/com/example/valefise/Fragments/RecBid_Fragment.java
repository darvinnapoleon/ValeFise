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
import androidx.fragment.app.Fragment;

import com.example.valefise.R;
import com.example.valefise.consults.daoBidones;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.controller.conCliBidon;
import com.example.valefise.controller.conFragments;
import com.example.valefise.controller.conRecBid;
import com.example.valefise.model.Cli_bidon;
import com.example.valefise.model.Clientes;

import java.util.ArrayList;

public class RecBid_Fragment extends Fragment  {
    Activity a;
    ArrayList<Cli_bidon> lista;
    daoBidones daocli ;
    conRecBid conclibid;
    conFragments confragments;
    public  RecBid_Fragment(Activity a){
        this.a = a;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recbid, container, false);
       daocli = new daoBidones(a);
       confragments = new conFragments();
        //listar cliente
      final ListView liscli = (ListView) view.findViewById(R.id.lv_cli);
      lista = daocli.lisclibid1("");
    conclibid=new conRecBid(a,lista,daocli);
    liscli.setAdapter(conclibid);

        //buscar cliente
       final Button btbuscli = (Button) view.findViewById(R.id.bt_buscli);
        final EditText etnomcli = (EditText) view.findViewById(R.id.et_nomcli);
        btbuscli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista = daocli.lisclibid1(etnomcli.getText().toString());
                conclibid.notifyDataSetChanged();
            }
        });

        final Button btrecbid = (Button) view.findViewById(R.id.bt_vol);
        btrecbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confragments.ret_Fragment( a, new Bid_Fragment(a));
            }
        });
        return  view;

    }

}
