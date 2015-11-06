package adapter;

import java.util.List;



import com.example.malala.R;
import com.example.model.Model_Detail_Kuliner;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class Adapter_Detail_Kuliner extends ArrayAdapter<Model_Detail_Kuliner>{

	private List<Model_Detail_Kuliner> listDetailkuliner;
	private Context context;
	String idkuliner;
	//String urlFoto = "http://malala.16mb.com/malala/app/";
	String urlFoto = "http://10.0.3.2/malala/app/";
	
	
	public Adapter_Detail_Kuliner(Context context, int resource, List<Model_Detail_Kuliner> object) {
		super(context, resource, object);
		this.context = context;
		this.listDetailkuliner = object;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.detil_kuliner, parent, false);
		
		
		
		Model_Detail_Kuliner detil = listDetailkuliner.get(position);
		
		TextView tvNama = (TextView) view.findViewById(R.id.nama_detail_kuliner);
		tvNama.setText(detil.getNama_kuliner());
		
		
		TextView tvLokasi = (TextView) view.findViewById(R.id.lokasi_detail_kuliner);
		tvLokasi.setText(detil.getLokasi());
		
		
		TextView tvKeterangan = (TextView) view.findViewById(R.id.info_detail_kuliner);
		tvKeterangan.setText(detil.getKeterangan());
		
		ImageView image = (ImageView) view.findViewById(R.id.fototempatwisatadetail);
		Picasso.with(this.context).load(urlFoto + detil.getFoto()).resize(50, 50).into(image);
		
	
		
		idkuliner = detil.getIdkuliner();
		
		Log.d("id wisata list detail ==>", idkuliner);
		
		return view;
		

	}
	
}