package com.example.valefise.controller;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.valefise.R;

public class conFragments {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentActivity myContext;
    public void ret_Fragment(Activity a, Fragment fr){
        myContext = (FragmentActivity) a;
        fragmentManager = myContext.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fr);
        fragmentTransaction.commit();
    }
}
