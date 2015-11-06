package tabhost.malala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import com.example.malala.HttpManager;
import com.example.malala.JSONParser;
import com.example.malala.R;
import com.example.model.Model_Kuliner;
import detail.malala.Detail_Kuliner;
import adapter.Adapter_Kuliner;
import alamatrestfull.alamat;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class List_penganan_khas extends Activity {
	

	String url,Modelkulinerrr;
	String idkuliner="";
	TextView namakuliner;
	private ListView listView;
	
	HttpManager httpManager;
	Adapter_Kuliner adapter;
	
	private List<ReadKuliner> reads;
	
	JSONParser jprser = new JSONParser();
	ArrayList<HashMap<String, String>> accountsList;
	List<Model_Kuliner> kulinerListNew = new ArrayList<Model_Kuliner>();
	JSONArray accounts =null;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_kabupaten_kota);
        
        Intent intent = getIntent();
        idkuliner = intent.getStringExtra("id_kuliner");
        url = alamat.URl_GET_KULINERR;
        
        reads = new ArrayList<ReadKuliner>();
        httpManager = new HttpManager(getApplicationContext());
        adapter = new Adapter_Kuliner (this, R.layout.itemlistkabupaten,kulinerListNew); 
        listView = (ListView)findViewById(R.id.listViewkabupatenkota);
      

        
        
        new ReadKuliner().execute();
        
       
        
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	
         @Override
        	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        	 
        	 Model_Kuliner kulinerM = kulinerListNew.get(position);
				pindahPage(kulinerM);
        	  }
        
        });
        
    		}
    private void pindahPage(Model_Kuliner kulinerModel){
		
		Intent inten = new Intent(List_penganan_khas.this, Detail_Kuliner.class);
		
		inten.putExtra("id_kuliner", kulinerModel.getIdkuliner());
		
		
		
		startActivity(inten);
	}
    

   
    private void updateList() {
    	
            Adapter_Kuliner adapter = new Adapter_Kuliner (getApplicationContext(), R.layout.listkuliner, kulinerListNew);
            listView.setAdapter(adapter);
    
    }
        
   class ReadKuliner extends AsyncTask<String, String, String> {
    		
        	String success, repons;
    		@Override
    		protected void onPreExecute() {
    			reads.add(this);
    		}

    		@Override
    		protected String doInBackground(String... params) {

    			try {
    				Modelkulinerrr = httpManager.getHttp(url);
    			} catch (NullPointerException e) {
    				
    				e.printStackTrace();
    			}

    			return Modelkulinerrr;
    		}
        
    	@Override
    		protected void onPostExecute(String response) {

    		 Log.d("Response nya", response.toString());
              kulinerListNew = Model_Kuliner.parseFeed(response);
           updateList();
    			
    						
    			
    			

    	}  	
    	
   }
    }


