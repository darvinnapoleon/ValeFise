package com.example.valefise.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.valefise.R;
import com.example.valefise.consults.daoCliTemporales;
import com.example.valefise.consults.daoVales;
import com.example.valefise.controller.conFragments;
import com.example.valefise.controller.conconCod;
import com.example.valefise.controller.validaciones;
import com.example.valefise.model.Cli_Temporal;
import com.example.valefise.model.Vales;
import java.util.ArrayList;

public class ConCod_Fragment extends Fragment {
    Activity a;
    public ConCod_Fragment(Activity a){
        this.a = a;
    }
    conFragments confragments;
    validaciones valcam;
    daoVales daoval;
    daoCliTemporales daoclitem;
    ArrayList<Cli_Temporal> lisclitem;
    ArrayList<Cli_Temporal> lisclitem1;
    ArrayList<Vales> lisdni;
    Vales val;
    Cli_Temporal cli;
    conconCod conconcod;
    @Nullable
    @Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_concod, container, false);
        confragments = new conFragments();
        daoval = new daoVales(a);
        daoclitem = new daoCliTemporales(a);
        valcam = new validaciones();
        final EditText telf = (EditText)view.findViewById(R.id.et_tel);
        final ListView lisconcod= (ListView) view.findViewById(R.id.lv_clitem);

        //listar clientes
        lisclitem1 = daoclitem.lisclitemp1();
        conconcod = new conconCod(a,lisclitem1, daoclitem);
        lisconcod.setAdapter(conconcod);
        //programamos el boton importar clientes
        Button bt_imp = (Button) view.findViewById(R.id.bt_impclitem);
        bt_imp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (isTimeAutomaticEnabled(a.getApplicationContext()) == false){
                    Toast.makeText(a, "Configura fecha y hora automatica", Toast.LENGTH_LONG).show();
                }else{
                    //obtienes la fecha con el siguiente formato
                    String fechaactual =  valcam.obtfechas("ddMMyy");
                    //validas el codigo de vale
                    int mes = valcam.valcodigovale(fechaactual);
                    //valida si el mes es mayor que 10
                    String valmes = valcam.validarmes(mes);
                    String codigovale = "06"+valmes+fechaactual.substring(4,6);

                //insertar clientes temporales
                    lisclitem = daoclitem.lisclitemp();
                    for (int i=0; i<lisclitem.size(); i++){
                        cli = new Cli_Temporal(lisclitem.get(i).getIdClientes(),
                                lisclitem.get(i).getNomClientes(),
                                lisclitem.get(i).getDniClientes(),"0","");
                        daoclitem.insclitem(cli);
                    }
                    //eliminas los clientes ya registrados
                    lisdni = daoval.lisdnival(codigovale);
                    for (int i=0; i<lisdni.size(); i++){
                        val = lisdni.get(i);
                        daoclitem.eliminar(val.getDnicli());
                    }
                    //listar cliente

                    lisclitem1 = daoclitem.lisclitemp1();
                    conconcod = new conconCod(a,lisclitem1, daoclitem);
                    lisconcod.setAdapter(conconcod);

                    //buscar cliente
                    /*Button busconcod = (Button) view.findViewById(R.id.bt_buscli);
                    final EditText nomcli = (EditText) view.findViewById(R.id.et_buscli);
                    busconcod.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lisclitem = daoclitem.lisclitemp1();
                            conconcod.notifyDataSetChanged();
                        }
                    });*/

            }
            }
        });

        //programamos el boton guardar vales
        Button bt_gua = (Button) view.findViewById(R.id.bt_guaval);
        bt_gua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisclitem = daoclitem.lisclitemp();
                for (int i=0; i<lisdni.size(); i++){
                    val = new Vales(1,lisclitem.get(i).getDniClientes(), lisclitem.get(i).getCodclitemp(),
                            "", "", "", 1);
                    daoval.insval(val);
                }

            }
        });

        Button bt_sig = (Button) view.findViewById(R.id.bt_inscod);
        bt_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confragments.ret_Fragment( a, new InsCodFragment(a));

            }
        });
    return  view;
}

    public static boolean isTimeAutomaticEnabled(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            return Settings.Global.getInt(context.getContentResolver(),Settings.Global.AUTO_TIME, 0) == 1;
        }else{
            return android.provider.Settings.System.getInt(context.getContentResolver(), Settings.System.AUTO_TIME, 0) ==1;
        }
    }
}