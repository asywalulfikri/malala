package adapter;

import alamatrestfull.alamat;
import android.app.Activity;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import com.example.malala.R;
import com.example.model.Model_Kuliner;
import com.squareup.picasso.Picasso;



public class Adapter_Kuliner extends ArrayAdapter<Model_Kuliner> {
    private Context context;
    private List<Model_Kuliner> kulinerList;
    String idkuliner;
 

    
    
    public Adapter_Kuliner(Context context, int resource, List<Model_Kuliner> object) {
        super(context, resource, object);
        
        this.kulinerList = object;
        this.context = context;
    }
    
   

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    		
    		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    		
    		View view = inflater.inflate(R.layout.itemlistkuliner,parent,false);
    		
    		Model_Kuliner kulinerData = kulinerList.get(position);
    		
    			TextView namakuliner = (TextView)view.findViewById(R.id.namakuliner);
    	
    	        namakuliner.setText(kulinerData.getNama_kuliner());
    	        
    	        ImageView fotoo = (ImageView)view.findViewById(R.id.fotokuliner);
    	        
    	        
    	        
                Picasso.with(this.context).load(alamat.URl_FOTO_KULINER + kulinerData.getFoto()).resize(50, 50).into(fotoo);
                
               
    	
    	
    	
    	return view;
        
    }
    
  
    	
    }
    
   
