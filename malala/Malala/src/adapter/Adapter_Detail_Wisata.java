package adapter;

import java.util.List;



import com.example.malala.R;
import com.example.model.Model_DEtail_tempat_Wisata;
import com.squareup.picasso.Callback;
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
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class Adapter_Detail_Wisata extends ArrayAdapter<Model_DEtail_tempat_Wisata>{

	private List<Model_DEtail_tempat_Wisata> listDetailwisata;
	private Context context;
	String idwisata;
	private ProgressBar progressbar;
	// urlFoto = "http://malala.16mb.com/malala/app/";
	String urlFoto = "http://10.0.3.2/malala/app/";
	
	public Adapter_Detail_Wisata(Context context, int resource, List<Model_DEtail_tempat_Wisata> object) {
		super(context, resource, object);
		this.context = context;
		this.listDetailwisata = object;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.detiltempatwisata, parent, false);
		
		
		
		Model_DEtail_tempat_Wisata detil = listDetailwisata.get(position);
		
		TextView tvNama = (TextView) view.findViewById(R.id.nama_tempat_wisata_detail);
		tvNama.setText(detil.getNama_wisata());
		
		
		TextView tvLokasi = (TextView) view.findViewById(R.id.lokasi_tempat_wisata_detail);
		tvLokasi.setText(detil.getLokasi());
		
		TextView tvJarak = (TextView) view.findViewById(R.id.Jarak_detil);
		tvJarak.setText(detil.getJarak());
		
		TextView tvAkses = (TextView) view.findViewById(R.id.Akses_lokasi);
		tvAkses.setText(detil.getAkses());
		
		TextView tvKeterangan = (TextView) view.findViewById(R.id.ketetrangan_detail_tempat_wisata);
		tvKeterangan.setText(detil.getKeterangan());
		
		ImageView image = (ImageView) view.findViewById(R.id.fototempatwisatadetail);
		// menambahkan progress bar foto
		
		Picasso.with(this.context).load(urlFoto + detil.getFoto()).resize(50, 50).into(image,new Callback() {
			 @Override
             public void onError() {
                 // TODO Auto-generated method stub

             }

             @Override
             public void onSuccess() {
                 // TODO Auto-generated method stub
                 progressbar.setVisibility(View.GONE);
             }

         });

 //Picasso.with(this).load(urlFoto+detil.getFoto()).resize(50,50) into(target);

		
		

		idwisata = detil.getIdwisata();
		
		Log.d("id wisata list detail ==>", idwisata);
		
		return view;
		

	}
	
}