package com.example.valefise;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.valefise.Fragments.CanVal_Fragment;
import com.example.valefise.Fragments.Main_Fragment;
import com.example.valefise.consults.daoClientes;
import com.example.valefise.controller.conClientes;
import com.example.valefise.controller.conInflaters;
import com.example.valefise.controller.validaciones;
import com.example.valefise.model.Clientes;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class LisCliActivity extends AppCompatActivity {
    //inicializas clases que vas a instanciar
conInflaters coninf ;
daoClientes daocli ;
validaciones valcam;
Clientes cli;
conClientes concli;
ArrayList<Clientes> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_main1);

        //objetos de flotantes y crud de clientes
        coninf = new conInflaters();
        daocli = new daoClientes(this);
        valcam = new validaciones();

        //listar cliente
        final ListView liscli = (ListView) findViewById(R.id.lv_cli);
        lista = daocli.liscli("");
        concli=new conClientes(this, lista, daocli);
        liscli.setAdapter(concli);

        //buscar cliente
        Button buscli = (Button) findViewById(R.id.bt_buscli);
        final EditText nomcli = (EditText) findViewById(R.id.et_buscli);
        buscli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista = daocli.liscli(nomcli.getText().toString());
                concli.notifyDataSetChanged();
            }
        });

        //agregar cliente a la bd
        Button agrcli = (Button) findViewById(R.id.bt_nuecli);
        agrcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int res = R.layout.view_cli;
                String tit="Datos del cliente";
                AlertDialog.Builder builder = coninf.retbuilder(res, LisCliActivity.this);
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
                           Toast.makeText(LisCliActivity.this, "Campos vacios o incompletos", Toast.LENGTH_SHORT).show();
                           return;
                       }
                       //evitar que se registre clientes duplicados
                        if (daocli.vercli(dnicli.getText().toString()) == 0){
                           cli = new Clientes(0,""+nomcli.getText(),""+dnicli.getText(), "1");
                            daocli.inscli(cli);
                            Toast.makeText(LisCliActivity.this, "cliente registrado", Toast.LENGTH_SHORT).show();
                            //actualizar la lista de clientes
                            lista = daocli.liscli("");
                            concli.notifyDataSetChanged();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(LisCliActivity.this, "el cliente ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        //importar cliente
        daocli.impcli(LisCliActivity.this, liscli);
        //abrir los directorios
        Button impcli = (Button) findViewById(R.id.bt_impcli);
        impcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daocli.formImportacion();
                Toast.makeText(LisCliActivity.this, "Llenar el archivo clientes.cvs", Toast.LENGTH_SHORT).show();
                Intent mat = new Intent(LisCliActivity.this,ExpDirectorio.class);
                startActivity(mat);
            }
        });
    }
}