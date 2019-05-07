package com.cdac.myfinalapplication;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MydatauploadService extends Service {
    String URL="";
    public MydatauploadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("service child Thread id", String.valueOf(Thread.currentThread().getId()));
                startUploading();
            }
        }).start();
        return  START_NOT_STICKY;
    }

    private void startUploading() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("1")) {
                    Toast.makeText(getApplicationContext(), "Product add", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Product not add", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap=new HashMap<>();
                //stringStringHashMap.put("name",strname);
                //stringStringHashMap.put("price",strprice);
                //stringStringHashMap.put("desc",strdesc);
                stringStringHashMap.put("choice","3");
                return stringStringHashMap;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(MydatauploadService.this);
        requestQueue.add(stringRequest);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
