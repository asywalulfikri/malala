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
import com.example.model.Model_Kabupaten_Kota;
import com.squareup.picasso.Picasso;



public class Adapter_Kabupaten_Kota extends ArrayAdapter<Model_Kabupaten_Kota> {
    private Context context;
    private List<Model_Kabupaten_Kota> kabupatenkotaList;
    String idkabupaten;
 

    
    
    public Adapter_Kabupaten_Kota(Context context, int resource, List<Model_Kabupaten_Kota> object) {
        super(context, resource, object);
        
        this.kabupatenkotaList = object;
        this.context = context;
    }
    
   

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    		
    		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    		
    		View view = inflater.inflate(R.layout.itemlistkabupaten,parent,false);
    		
    		Model_Kabupaten_Kota kabupatenkotaData = kabupatenkotaList.get(position);
    		
    			TextView namakabupaten = (TextView)view.findViewById(R.id.namakabupaten);
    	
    	        namakabupaten.setText(kabupatenkotaData.getNama_kabupaten());
    	        
    	        ImageView fotoo = (ImageView)view.findViewById(R.id.fotokabupaten);
    	        
                Picasso.with(this.context).load(alamat.URl_FOTO_KABUPATEN + kabupatenkotaData.getFoto()).resize(50, 50).into(fotoo);
               
    	
    	
    	
    	return view;
        
    }
    
  
    	
    }
    
   
