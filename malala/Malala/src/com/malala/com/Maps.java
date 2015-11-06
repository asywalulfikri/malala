package com.malala.com;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.example.malala.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class Maps extends FragmentActivity implements
		LocationListener, OnClickListener {
	
	private GoogleMap googleMap;
	double latitude, longitude;
	
	@Override
	protected void onCreate(Bundle arg0) {
		CekGPS();
		super.onCreate(arg0);
		setContentView(R.layout.lokasi_maps);

		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
		.findFragmentById(R.id.map);
		
		googleMap = fm.getMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		googleMap.setMyLocationEnabled(true);	
	}

	
	
	public void CekGPS() {
        try {

                /* pengecekan GPS hidup / tidak */
                LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                
                
                
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Info");
                        builder.setMessage("Anda akan mengaktifkan GPS?");

                        builder.setPositiveButton("Ya",
                                        new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                int which) {
                                                	
                                                        Intent i = new Intent(
                                                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                                        
                                                        if(latitude!=0 && longitude !=0){
                                            				Toast.makeText(getApplicationContext(), "Latitude : "+latitude+" Longitude : "+longitude, Toast.LENGTH_LONG).show();
                                            				
                                            			}
                                                        
                                                        startActivity(i);
                                                }
                                        });
                        

                        builder.setNegativeButton("Tidak",
                                        new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                int which) {
                                                        
                                                        dialog.dismiss();
                                                }
                                        });
                        builder.create().show();
                        
                        

                }

        } catch (Exception e) {
               
        }

        int status = GooglePlayServicesUtil
                        .isGooglePlayServicesAvailable(getBaseContext());

        // menampilkan status google play service
        if (status != ConnectionResult.SUCCESS) { 
                int requestCode = 10;
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
                                requestCode);
                dialog.show();

        } else { 
        	// Google Play Services tersedia

                try {
                        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                        // Membuat kriteria untuk penampungan provider
                        Criteria criteria = new Criteria();

                        // Mencari provider terbaik
                        String provider = locationManager.getBestProvider(criteria,
                                        true);

                        // Mendapatkan lokasi terakhir
                        Location location = locationManager
                                        .getLastKnownLocation(provider);

                        if (location != null) {
                                onLocationChanged(location);
                        }
                        locationManager.requestLocationUpdates(provider, 5000, 0, this);
                } catch (Exception e) {
                     

                }
        }
        
        
	}

	
	@Override
	public void onLocationChanged(Location lokasi) {
		// TODO Auto-generated method stub
		latitude= lokasi.getLatitude();
		longitude=lokasi.getLongitude();

	}
	
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

	
}
