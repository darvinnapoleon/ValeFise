package com.example.valefise.controller;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.valefise.consults.daoCliTemporales;
import com.example.valefise.model.Cli_Temporal;

public class ReceiveSms extends BroadcastReceiver {
    daoCliTemporales dao;
    Activity c = new Activity();
    Cli_Temporal cli;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String  msg_from;
            if (bundle != null){
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = ""+msgs[i].getMessageBody();
                        String dni;
                        String cod;
                        dni = msgBody.substring(5,13);
                        if(msgBody.length()< 100){
                            cod = msgBody.substring(44,58);
                        }else{
                            cod = msgBody.substring(93,107);
                        }
                            dao = new daoCliTemporales(context);
                            cli = new Cli_Temporal(1,"", ""+dni,  "1",""+cod);
                            dao.actclitemp(cli);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}
