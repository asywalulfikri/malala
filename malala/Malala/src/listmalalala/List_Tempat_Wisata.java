package listmalalala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import com.example.malala.HttpManager;
import com.example.malala.JSONParser;
import com.example.malala.MenuUtama;
import com.example.malala.R;
import com.example.model.Model_Tempat_Wisata;
import detail.malala.Detail_Tempat_Wisata;
import adapter.Adapter_Tempat_Wisata;
import alamatrestfull.alamat;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class List_Tempat_Wisata extends Activity {
	

	String url,Modeltempatwisataa;
	String idwisata="";
	String idkabupaten="";
	TextView namawisata,lokasi;
	//private ProgressDialog pDialog;
	private ListView listView1;
	
	HttpManager httpManager;
	Adapter_Tempat_Wisata adapter;
	
	private List<Readtempatwisata> readss;
	
	JSONParser jprser = new JSONParser();
	ArrayList<HashMap<String, String>> accountsList;
	List<Model_Tempat_Wisata> tempatwisataListNew = new ArrayList<Model_Tempat_Wisata>();
	JSONArray accounts =null;
	
	
	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.listtempatwisata);
        
        Intent intent = getIntent();
        idwisata = intent.getStringExtra("id_wisata");
        idkabupaten = intent.getStringExtra("idk");
        url = alamat.URL_TEMPAT_WISATA + idkabupaten;
        
        readss = new ArrayList<Readtempatwisata>();
        httpManager = new HttpManager(getApplicationContext());
        adapter = new Adapter_Tempat_Wisata (this, R.layout.itemtmpatwisata,tempatwisataListNew); 
        listView1 = (ListView)findViewById(R.id.listTempatWisata);
       // listView1.setChoiceMode(listView1.CHOICE_MODE_SINGLE);

        
        
        new Readtempatwisata().execute();
        
        listView1.setClickable(true);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	
         @Override
        	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        	 
        	 Model_Tempat_Wisata pindah = tempatwisataListNew.get(position);
				pindahPage(pindah);
        	  }
        
        });
    }
 private void pindahPage(Model_Tempat_Wisata tempatwisataModel){
		
		Intent inten = new Intent(List_Tempat_Wisata.this, Detail_Tempat_Wisata.class);
		
		inten.putExtra("id_wisata", tempatwisataModel.getIdwisata());
		
		
		
		startActivity(inten);
	}
        
       
       
	
    private void updateListwisata() {
    	
        Adapter_Tempat_Wisata adapterwisata = new Adapter_Tempat_Wisata (getApplicationContext(), R.layout.listtempatwisata, tempatwisataListNew);
        listView1.setAdapter(adapterwisata);

}
    

   
  
        
   class Readtempatwisata extends AsyncTask<String, String, String> {
    		
        	String success, repons;
    		@Override
    		protected void onPreExecute() {
    			readss.add(this);
    		}

    		@Override
    		protected String doInBackground(String... params) {

    			try {
    				Modeltempatwisataa = httpManager.getHttp(url);
    			} catch (NullPointerException e) {
    				
    				e.printStackTrace();
    			}

    			return Modeltempatwisataa;
    		}
        
    	@Override
    		protected void onPostExecute(String response) {

    		 Log.d("Response nya", response.toString());
              tempatwisataListNew = Model_Tempat_Wisata.parseFeed(response);
             updateListwisata();
    			
    						
    			
    			

    	} 
    	  
    	
   }
   @Override
  	public boolean onOptionsItemSelected(MenuItem item) {
  	    switch (item.getItemId()) {
  	        case android.R.id.home:
  	            // app icon in action bar clicked; go home
  	            Intent intent = new Intent(this, List_Kabupaten_Kota.class);
  	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  	            startActivity(intent);
  	            return true;
  	        default:
  	            return super.onOptionsItemSelected(item);
  	    }
  	}
    }


