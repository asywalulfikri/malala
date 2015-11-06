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
import com.example.malala.R;
import com.squareup.picasso.Picasso;
import alamatrestfull.alamat;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail_Kuliner extends Activity {
	TextView namakuliner,lokasi,keterangan;
	ImageView fotokuliner;
	String idkuliner="";
	
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
	        setContentView(R.layout.detil_kuliner);
	        
	        Intent intent = getIntent();
			idkuliner = intent.getStringExtra("id_kuliner");
	       
			httpManager = new HttpManager(getApplicationContext());
			
			Log.d("id kuliner", idkuliner);
	        url = alamat.URL_DETAIL_KULINER +idkuliner;

	        Log.d("Url Detail kuliner ==>", url);
	        
	        namakuliner = (TextView)findViewById(R.id.nama_detail_kuliner);
	        lokasi = (TextView)findViewById(R.id.lokasi_detail_kuliner);
	        
	        fotokuliner = (ImageView)findViewById(R.id.foto_detil_kuliner);
	        keterangan = (TextView)findViewById(R.id.info_detail_kuliner);
	        
	        
	        new LoadProfile().execute();
	       
	     

}
	
	        
	 
	 class LoadProfile extends AsyncTask<String, String, String> {
	    	String urlfoto ="http://malala.16mb.com/app/";
	    	//String urlfoto ="http://10.0.3.2/malala/app/";
	    	


	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(Detail_Kuliner.this);
	            pDialog.setMessage("Tunggu.....");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
	    
	    protected String doInBackground(String... args) {
	    	 List<NameValuePair> params = new ArrayList<NameValuePair>();
	           
	    	 JSONObject json = jParser.makeHttpRequest(url, "POST", params);
	         Log.d("All Accounts: ", json.toString());
	         try {
	 			Log.d("response", json.getString("response"));
	 			Log.d("Data Detail kuliner ==>", json.getString("data"));
	         	JSONObject sukses = json.getJSONObject("response");
	             int success = sukses.getInt(TAG_SUCCESS);
	             if (success == 1) {
	                 accounts = json.getJSONArray("data");
	                for (int i = 0; i < accounts.length(); i++) {
	                    JSONObject c = accounts.getJSONObject(i);
	                    fill_profile[0] = (c.getString("nama_kuliner"));
	                    fill_profile[1] = (c.getString("lokasi"));
	                    fill_profile[2] = (c.getString("keterangan"));
						fill_profile[3] = (c.getString("foto_kuliner"));
						 

	                }
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    protected void onPostExecute(String response) {
	    	
	        
	    	pDialog.dismiss();
	        namakuliner.setText(fill_profile[0]);
	        lokasi.setText(fill_profile[1]);
	        keterangan.setText(fill_profile[2]);
	       	Picasso.with(getApplicationContext())
					.load(urlfoto + fill_profile[3])
					.placeholder(R.drawable.ic_launcher).noFade()
					.into(fotokuliner);
			
		
	    }
	    
	  }
}
