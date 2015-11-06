package com.example.malala;

import tabhost.malala.AndroidTabAndListView;
import listmalalala.List_Kabupaten_Kota;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MenuUtama extends Activity {
	
	private ListView listview1;
	
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.menu);
	        
	        
	        Profil weather_data[] = new Profil[]
			        {
			            new Profil(R.drawable.jamgadang, "Tempat Wisata"),
			            new Profil(R.drawable.rendangg, "Kuliner Khas"),
			            new Profil(R.drawable.kebudayaan,"Kebudayaan"),
			            new Profil(R.drawable.aboutt, "Tentang Aplikasi"),
			            
			        
			        };
	        
	        ProfilAdapterMenu_Utama adapter = new ProfilAdapterMenu_Utama(this,
	                R.layout.itemprofil, weather_data);
	 
	        listview1 = (ListView)findViewById(R.id.lisviewmenu);
	 
	        listview1.setAdapter(adapter); 
	        
	        listview1.setOnItemClickListener(new ListClickHandler());
	        
	}
	 public class ListClickHandler implements OnItemClickListener{
		    @Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {


		        // get selected items

		        if(position == 0)
		        {
		            Intent a= new Intent(MenuUtama.this, List_Kabupaten_Kota.class);
		            startActivity(a);
		        } else if(position == 1)
		        {
		             Intent b= new Intent(MenuUtama.this,AndroidTabAndListView.class);
		             startActivity(b);
		        } else if(position == 2){
		        	Intent in = new Intent(MenuUtama.this, About.class);
					startActivity(in);
		        }

		    }  
		    
	        

	


	 }
	 
	 @Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.menuprofil, menu);
		    return true;
		}
	//Jika Tekan Tombol back
		public void onBackPressed() {
			 exit();//Pergi ke method exit 
		}
		private void exit() {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Apakah Akan keluar aplikasi ?")
			.setCancelable(false)//tidak bisa tekan tombol back
			//jika pilih yess
			.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					finish();
				}
			})
			//jika pilih no
			.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			}).show();

		}
	}
