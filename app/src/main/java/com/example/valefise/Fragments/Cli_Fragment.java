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
import com.example.valefise.controller.conClientes;
import com.example.valefise.controller.conFragments;
import com.example.valefise.controller.conInflaters;
import com.example.valefise.controller.validaciones;
import com.example.valefise.model.Clientes;

import java.util.ArrayList;

public class Cli_Fragment extends Fragment  {
    //inicializas clases que vas a instanciar
    conInflaters coninf ;
    daoClientes daocli ;
    validaciones valcam;
    Clientes cli;
    conClientes concli;
    ArrayList<Clientes> lista;
    Activity a;
    conFragments confragments;
    public Cli_Fragment(Activity a){
        this.a = a;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //carga el /layout/activity_lis_cli
        View view = inflater.inflate(R.layout.activity_lis_cli, container, false);
        //carga los objetos que se usaran
        coninf = new conInflaters();
        daocli = new daoClientes(a);
        valcam = new validaciones();
        confragments = new conFragments();

        //listar cliente
        final ListView liscli = (ListView) view.findViewById(R.id.lv_cli);
        lista = daocli.liscli("");
        concli=new conClientes(a, lista, daocli);
        liscli.setAdapter(concli);

        //buscar cliente
        Button buscli = (Button) view.findViewById(R.id.bt_buscli);
        final EditText nomcli = (EditText) view.findViewById(R.id.et_buscli);
        buscli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista = daocli.liscli(nomcli.getText().toString());
                concli.notifyDataSetChanged();
            }
        });

        //agregar cliente a la bd
        Button agrcli = (Button) view.findViewById(R.id.bt_nuecli);
        agrcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res = R.layout.view_cli;
                String tit="Datos del cliente";
                AlertDialog.Builder builder = coninf.retbuilder(res, a);
                final AlertDialog dialog = coninf.retdialog(builder, tit);

                //inicializa el nombre y el dni del cliente y el boton registrar
                final EditText nomcli = (EditText)dialog.findViewById(R.id.et_nomcli);
                final EditText dnicli = (EditText)dialog.findViewById(R.id.et_dnicli);
                final Button inscli = (Button)dialog.findViewById(R.id.bt_regcli);

                // click en el boton de registrar clientes
                inscli.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int valcam1 = valcam.valcampos(nomcli.getText().toString(), "text");
                        int valdni = valcam.valcampos(dnicli.getText().toString(), "dni");
                        if(valcam1 == 0 || valdni == 0){
                            Toast.makeText(a, "Campos vacios o incompletos", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //evitar que se registre clientes duplicados
                        if (daocli.vercli(dnicli.getText().toString()) == 0){
                            cli = new Clientes(0,""+nomcli.getText(),""+dnicli.getText(), "1");
                            daocli.inscli(cli);
                            Toast.makeText(a, "cliente registrado", Toast.LENGTH_SHORT).show();
                            //actualizar la lista de clientes
                            lista = daocli.liscli("");
                            concli.notifyDataSetChanged();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(a, "el cliente ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        //importar cliente
        Bundle objnomdir = getArguments();
        if (objnomdir!= null) {
            String nomdir = objnomdir.getString("directorio");
            daocli.impcli(a, liscli, nomdir);
        }
        //abrir los directorios
        Button impcli = (Button) view.findViewById(R.id.bt_impcli);
        impcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daocli.forImportacion1();
                Toast.makeText(a, "Llenar ImpClientes/clientes.xls", Toast.LENGTH_LONG).show();
                confragments.ret_Fragment( a, new ExpDir_Fragment(a));

            }
        });
        return  view;


    }

}
