package detail.malala;



import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.malala.HttpManager;
import com.example.malala.JSONParser;
import com.example.malala.MainActivity;
import com.example.malala.R;
import com.squareup.picasso.Picasso;
import alamatrestfull.alamat;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
@SuppressLint("NewApi")
public class Detail_Tempat_Wisata extends Activity {
	TextView namawisata,lokasi,keterangan,jarak,akses;
	ImageView fototempatwisata;
	ImageView maps;
	String idwisata="";
	
	String url="";
	private ProgressDialog pDialog;
	
	
	 ArrayList<HashMap<String, String>> accountsList;
	 JSONArray accounts = null;
	 HttpManager httpManager;
	 private String [] fill_profile = new String[10];
	 JSONParser jParser = new JSONParser();
	 private static final String TAG_SUCCESS = "success";
	 
	
	
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.detiltempatwisata);
	        
	        Intent intent = getIntent();
			idwisata = intent.getStringExtra("id_wisata");
	       
			httpManager = new HttpManager(getApplicationContext());
			
			Log.d("id tempatwisata", idwisata);
	        url = alamat.URL_DETAIL_TEMPAT_WISATA +idwisata;

	        Log.d("Url Detail tempat wisata ==>", url);
	        
	        namawisata = (TextView)findViewById(R.id.nama_tempat_wisata_detail);
	        lokasi = (TextView)findViewById(R.id.lokasi_tempat_wisata_detail);
	        
	        fototempatwisata = (ImageView)findViewById(R.id.fototempatwisatadetail);
	        keterangan = (TextView)findViewById(R.id.ketetrangan_detail_tempat_wisata);
	        jarak = (TextView)findViewById(R.id.Jarak_detil);
	        akses = (TextView)findViewById(R.id.Akses_lokasi);
	        
	        maps = (ImageView)findViewById(R.id.button_maps);
            maps.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent inten = new Intent(Detail_Tempat_Wisata.this, MainActivity.class);
					inten.putExtra("id_wisata",idwisata);
					startActivity(inten);
					
				}
			});
	        
	      
	        
	        new LoadProfile().execute();
	       
	     

}
	
	        
	 
	 class LoadProfile extends AsyncTask<String, String, String> {
	    	//String urlfoto ="http://malala.16mb.com/app/";
	    	String urlfoto ="http://10.0.3.2/malala/app/";
	    	


	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Detail_Tempat_Wisata.this);
	            pDialog.setMessage("Tunggu.....");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
	    
	    protected String doInBackground(String... args) {
	    	 List<NameValuePair> params = new ArrayList<NameValuePair>();
	           
	    	 JSONObject json = jParser.makeHttpRequest(url, "POST", params);
	        // Log.d("All Accounts: ", json.toString());
	         try {
	 			//Log.d("response", json.getString("response"));
	 			//Log.d("Data Detail tempat wisata ==>", json.getString("data"));
	         	JSONObject sukses = json.getJSONObject("response");
	             int success = sukses.getInt(TAG_SUCCESS);
	             if (success == 1) {
	                 accounts = json.getJSONArray("data");
	                for (int i = 0; i < accounts.length(); i++) {
	                    JSONObject c = accounts.getJSONObject(i);
	                     fill_profile[0] = (c.getString("nama_tempat_wisata"));
	                     fill_profile[1] = (c.getString("lokasi"));
	                     fill_profile[2] = (c.getString("keterangan"));
						 fill_profile[3] = (c.getString("foto_tempat_wisata"));
						 fill_profile[4] = (c.getString("jarak"));
						 fill_profile[5] = (c.getString("akses"));

	                }
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    protected void onPostExecute(String response) {
	    	
	    	//Log.d("Response nya", response.toString());
	        
	    	pDialog.dismiss();
	        namawisata.setText(fill_profile[0]);
	        lokasi.setText(fill_profile[1]);
	        keterangan.setText(fill_profile[2]);
	        jarak.setText(fill_profile[4]);
	        akses.setText(fill_profile[5]);
	       	Picasso.with(getApplicationContext())
					.load(urlfoto + fill_profile[3])
					.placeholder(R.drawable.ic_launcher).noFade()
					.into(fototempatwisata);
			
		
	    }
	    
	  }
	}
