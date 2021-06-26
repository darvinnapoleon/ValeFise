package com.example.valefise.controller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

public class conInflaters {
    public AlertDialog retdialog(AlertDialog.Builder bui, String tit){
        AlertDialog dialog = bui.create();
        dialog.setTitle(tit);
        dialog.getWindow().setLayout(500, 500);
        dialog.show();
        return dialog;
    }
    public AlertDialog.Builder retbuilder(int res, Activity a){
        LayoutInflater inflater = a.getLayoutInflater();
        View vi = inflater.inflate(res, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        return builder.setView(vi);
    }
}
