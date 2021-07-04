package com.example.valefise;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.valefise.consults.daoPagos;
import com.example.valefise.model.Pagos;
import com.example.valefise.model.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    daoPagos daopag;
    ArrayList<Integer> listab;
    ArrayList<Pagos> lispag;
    Pagos pag;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Usuario> lisUsuario = new ArrayList<Usuario>();
    ArrayAdapter<Usuario> arrayAdapterUsuario;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarFirebase();
        //listUsuario();
        //if la fecha no es automatica mostrara toast en pantalla
      if (isTimeAutomaticEnabled(getApplicationContext()) == false){
            Toast.makeText(this, "Configura fecha y hora automatica", Toast.LENGTH_LONG).show();
        }else{
           daopag = new daoPagos(MainActivity.this);
           listab = daopag.verTabla();
           //if la tabla no tiene registros se inserta la fecha de inicio del pago
        if(listab.get(0).equals(0)){
                LocalDate fecact = LocalDate.now();
                pag = new Pagos(0, ""+fecact);
                //accede al metodo insertar pago y le envia id y fecha de pago actual
                daopag.inspag(pag);
            Intent liscli = new Intent(MainActivity.this, MainActivity1.class);
            startActivity(liscli);
            } else{
            //accede al metodo para ver la fecha de pagos
                lispag = daopag.verPagos();
                final LocalDate fecpag = LocalDate.parse(lispag.get(0).getFecpag());
                final LocalDate fecact = LocalDate.now();
                long dias = ChronoUnit.DAYS.between(fecpag, fecact);
                //if dias es mayor a 120 debe de verificar su pago
                if(dias>120){
                    String pago = "pago";
                    //verifica si pago y si pago se va  a la actividad lista de clientes sino se queda ahi no mas
                    if(pago.equals("no pago")){
                        Toast.makeText(this, "debes de pagar", Toast.LENGTH_SHORT).show();
                    }else{
                        pag = new Pagos(1, ""+fecact);
                        daopag.actpag(pag);
                        Intent liscli = new Intent(MainActivity.this, MainActivity1.class);
                        startActivity(liscli);
                    }
                }else {
                  Intent liscli = new Intent(MainActivity.this, MainActivity1.class);
                  startActivity(liscli);
                }
            }
        }
    }

    private void listUsuario() {
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lisUsuario.clear();
                for (DataSnapshot objSnapshot : snapshot.getChildren()){
                    Usuario u = objSnapshot.getValue(Usuario.class);
                    lisUsuario.add(u);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static boolean isTimeAutomaticEnabled(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            return Settings.Global.getInt(context.getContentResolver(),Settings.Global.AUTO_TIME, 0) == 1;
        }else{
            return android.provider.Settings.System.getInt(context.getContentResolver(), Settings.System.AUTO_TIME, 0) ==1;
        }
    }
}