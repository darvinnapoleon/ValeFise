package com.example.valefise;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.valefise.Fragments.Bid_Fragment;
import com.example.valefise.Fragments.CanVal_Fragment;
import com.example.valefise.Fragments.Cli_Fragment;
import com.example.valefise.Fragments.ConCod_Fragment;
import com.example.valefise.controller.conFragments;
import com.google.android.material.navigation.NavigationView;

public class MainActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    conFragments conFragment;
    int REQUEST_CODE = 200;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_main1);
        pedirPermisos();
        //menu
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_View);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        //controla que fragment se debe vizualizar

        conFragment = new conFragments();
        conFragment.ret_Fragment(this, new Cli_Fragment(this));
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void pedirPermisos() {
        int permisoSms = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int permisoalmacen = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permisoalmacen1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permisoSms != PackageManager.PERMISSION_GRANTED && permisoalmacen != PackageManager.PERMISSION_GRANTED&& permisoalmacen != PackageManager.PERMISSION_GRANTED && permisoalmacen1 != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.nav_ini){
            conFragment.ret_Fragment(this, new Cli_Fragment(this));
        }
        if(item.getItemId() == R.id.nav_con){
            conFragment.ret_Fragment(this, new ConCod_Fragment(this));
        }
        if(item.getItemId() == R.id.nav_bid){
            conFragment.ret_Fragment(this, new Bid_Fragment(this));
        }
        if(item.getItemId() == R.id.nav_can){
            conFragment.ret_Fragment(this, new CanVal_Fragment(this));
        }
        return false;
    }
}
