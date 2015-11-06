package listmalalala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import com.example.malala.HttpManager;
import com.example.malala.JSONParser;
import com.example.malala.MenuUtama;
import com.example.malala.R;
import com.example.model.Model_Kabupaten_Kota;
import adapter.Adapter_Kabupaten_Kota;
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

public class List_Kabupaten_Kota extends Activity {
	

	String url,Modelkabupatenkotaa;
	String idkabupaten="";
	TextView namakabupaten;
	private ListView listView;
	
	HttpManager httpManager;
	Adapter_Kabupaten_Kota adapter;
	
	private List<ReadKabupatenkota> reads;
	
	JSONParser jprser = new JSONParser();
	ArrayList<HashMap<String, String>> accountsList;
	List<Model_Kabupaten_Kota> kabupatenkotaListNew = new ArrayList<Model_Kabupaten_Kota>();
	JSONArray accounts =null;
	
	
	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.list_kabupaten_kota);
        
        Intent intent = getIntent();
        idkabupaten = intent.getStringExtra("idk");
        url = alamat.URL_GET_KABUPATEN_KOTA;
        
        reads = new ArrayList<ReadKabupatenkota>();
        httpManager = new HttpManager(getApplicationContext());
        adapter = new Adapter_Kabupaten_Kota (this, R.layout.itemlistkabupaten,kabupatenkotaListNew); 
        listView = (ListView)findViewById(R.id.listViewkabupatenkota);
     

        
        
        new ReadKabupatenkota().execute();
        
       
        
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	
         @Override
        	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        	 
        	 Model_Kabupaten_Kota ternakM = kabupatenkotaListNew.get(position);
				pindahPage(ternakM);
        	  }
        
        });
        
    		}
  
    
    private void pindahPage(Model_Kabupaten_Kota kabupatenkotaModel){
		
		Intent inten = new Intent(List_Kabupaten_Kota.this, List_Tempat_Wisata.class);
		
		inten.putExtra("idk", kabupatenkotaModel.getIdkabupaten());
		
		
		
		startActivity(inten);
	}
    

   
    private void updateList() {
    	
            Adapter_Kabupaten_Kota adapter = new Adapter_Kabupaten_Kota (getApplicationContext(), R.layout.list_kabupaten_kota, kabupatenkotaListNew);
            listView.setAdapter(adapter);
    
    }
        
   class ReadKabupatenkota extends AsyncTask<String, String, String> {
    		
        	String success, repons;
    		@Override
    		protected void onPreExecute() {
    			reads.add(this);
    		}

    		@Override
    		protected String doInBackground(String... params) {

    			try {
    				Modelkabupatenkotaa = httpManager.getHttp(url);
    			} catch (NullPointerException e) {
    				
    				e.printStackTrace();
    			}

    			return Modelkabupatenkotaa;
    		}
        
    	@Override
    		protected void onPostExecute(String response) {

    		 Log.d("Response nya", response.toString());
              kabupatenkotaListNew = Model_Kabupaten_Kota.parseFeed(response);
           updateList();
    			
    						
    			
    			

    	}  	
    	
    	
   }
   
   @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            Intent intent = new Intent(this,MenuUtama.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    }


