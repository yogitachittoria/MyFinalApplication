package com.cdac.myfinalapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyNetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if(info!=null && info.getType()==ConnectivityManager.TYPE_WIFI)
        {
            Intent intent1=new Intent(context,MydatauploadService.class);
            if(info.isConnected())
                context.startService(intent1);
            else
                context.stopService(intent1);


        }

    }
}
