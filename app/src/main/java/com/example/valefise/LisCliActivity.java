package com.example.valefise;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.valefise.consults.daoClientes;
import com.example.valefise.controller.conInflaters;

public class LisCliActivity extends AppCompatActivity {
conInflaters coninf ;
daoClientes daocli ;
int REQUEST_CODE = 200;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lis_cli);
        //pedir permisos
        pedirPermisos();
        //objetos de flotantes y crud de clientes
        coninf = new conInflaters();
        daocli = new daoClientes(LisCliActivity.this);
        //agregar cliente
     Button agrcli = (Button) findViewById(R.id.bt_nuecli);
        agrcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int res = R.layout.view_cli;
                String tit="Datos del cliente";
                AlertDialog.Builder builder = coninf.retbuilder(res, LisCliActivity.this);
                AlertDialog dialog = coninf.retdialog(builder, tit);
                final EditText nomcli = (EditText)dialog.findViewById(R.id.et_nomcli);
                final Button inscli = (Button)dialog.findViewById(R.id.bt_regcli);
                inscli.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(LisCliActivity.this, ""+nomcli.getText(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        //listar cliente
        daocli.liscli("");
        //buscar cliente
        Button buscli = (Button) findViewById(R.id.bt_buscli);
        final EditText nomcli = (EditText) findViewById(R.id.et_buscli);
        buscli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daocli.liscli(nomcli.getText().toString());
            }
        });

        //importar cliente
        //ListView liscli = (ListView) findViewById(R.id.lv_cli);
        //daocli.impcli(LisCliActivity.this, liscli);
        //abrir los directorios
        Button impcli = (Button) findViewById(R.id.bt_impcli);
        impcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mat = new Intent(LisCliActivity.this,ExpDirectorio.class);
                startActivity(mat);
                //Toast.makeText(LisCliActivity.this, "hola amor", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void pedirPermisos() {
        int permisoSms = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int permisoalmacen = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permisoSms != PackageManager.PERMISSION_GRANTED && permisoalmacen != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }
}