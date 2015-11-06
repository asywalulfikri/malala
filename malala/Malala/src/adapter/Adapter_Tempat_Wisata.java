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
import com.example.model.Model_Tempat_Wisata;
import com.squareup.picasso.Picasso;



public class Adapter_Tempat_Wisata extends ArrayAdapter<Model_Tempat_Wisata> {
    private Context context;
    private List<Model_Tempat_Wisata> tempatwisataList;
    String idkabupaten;
    String id_wisata;
    TextView namatempatwisata,lokasi;

    
    
    public Adapter_Tempat_Wisata(Context context, int resource, List<Model_Tempat_Wisata> object) {
        super(context, resource, object);
        
        this.tempatwisataList = object;
        this.context = context;
    }
    
   

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    		
    		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    		
    		View view = inflater.inflate(R.layout.itemtmpatwisata,parent,false);
    		
    		Model_Tempat_Wisata tempatwisataData = tempatwisataList.get(position);
    		
    			 namatempatwisata = (TextView)view.findViewById(R.id.namatempatwisata);
    	
    	         namatempatwisata.setText(tempatwisataData.getNama_wisata());
    	        
    	         lokasi = (TextView)view.findViewById(R.id.lokasi);
    	    	
    	         lokasi.setText(tempatwisataData.getLokasi());
    	        
    	        ImageView foto = (ImageView)view.findViewById(R.id.fototempatwisata);
    	        
                Picasso.with(this.context).load(alamat.URl_FOTO_TEMPAT_WISATA + tempatwisataData.getFoto()).resize(50, 50).into(foto);
               
    	
    	
    	
    	return view;
        
    }
    
    static class NewsHolder{
    	
    	TextView itemNama;
    	ImageView foto;
    	
    }
    
   
}
