package com.cdac.myfinalapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final String LOG_TAG ="HomeActivity" ;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION =2 ;
    private FusedLocationProviderClient fusedLocationClient;
    //Location mylocation;
    Button fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fetch = findViewById(R.id.location);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLocation();
            }
        });
    }



    public void submit(View view) {

    }


    private void fetchLocation() {
        if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                Address address = null;

                                try
                                {
                                    Geocoder geocoder = new Geocoder(HomeActivity.this);
                                    double latitude  = location.getLatitude();
                                    double longitude = location.getLongitude();
                                    int maxResults = 1;
                                    List<Address> addresses = geocoder.getFromLocation
                                            (latitude, longitude, maxResults);

                                    if (addresses.size() > 0) {
                                        address = addresses.get(0);
                                        fetch.setText(address.getLocality());
                                    }
                                }
                                catch (IOException ex)
                                {
                                    Log.e(LOG_TAG, ex.getMessage());
                                }

                            } else {
                                Toast.makeText(HomeActivity.this, "location not find", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }

    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    fusedLocationClient.getLastLocation()
                            .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    // Got last known location. In some rare situations this can be null.
                                    if (location != null) {
                                        // Logic to handle location object
                                        //mylocation=location;
                                        Address address = null;

                                        try
                                        {
                                            Geocoder geocoder = new Geocoder(HomeActivity.this);
                                            double latitude  = location.getLatitude();
                                            double longitude = location.getLongitude();
                                            int maxResults = 1;
                                            List<Address> addresses = geocoder.getFromLocation
                                                    (latitude, longitude, maxResults);

                                            if (addresses.size() > 0) {
                                                address = addresses.get(0);
                                                fetch.setText(address.getLocality());
                                            }
                                        }
                                        catch (IOException ex)
                                        {
                                            Log.e(LOG_TAG, ex.getMessage());
                                        }
                                    } else {
                                        Toast.makeText(HomeActivity.this, "location not find", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else {
                   // Snackbar.make(view,"permission not available", Snackbar.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

}
