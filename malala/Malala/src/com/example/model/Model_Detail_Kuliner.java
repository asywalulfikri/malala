package com.example.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Model_Detail_Kuliner{
	
	private String idkuliner;
	private String idkategori;
    private String nama_kuliner;
    private String lokasi;
    private String foto;
    private String keterangan;
    
    
    
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getNama_kuliner() {
		return nama_kuliner;
	}
	public void setNama_kuliner(String nama_kuliner) {
		this.nama_kuliner = nama_kuliner;
	}
	
	public String getIdkategori(){
		return idkategori;
	}
	public void setIdkategori (String idkategori) {
		this.idkategori = idkategori;
	}
	
	
	public String getIdkuliner() {
		return idkuliner;
	}
	public void setIdkuliner(String idkuliner) {
		this.idkuliner = idkuliner;
	}
	public String getLokasi() {
		return lokasi;
	}
	public void setLokasi (String lokasi) {
		this.lokasi = lokasi;
	}
	public String getKeterangan() {
		return keterangan;
	}
	
	public void setKeterangan (String keterangan) {
		this.keterangan = keterangan;
	}
	
	public static List<Model_Detail_Kuliner> parseFeed(String response) {

		try {
			JSONObject object = new JSONObject(response);
			return parseFeed (object);

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Model_Detail_Kuliner> parseFeed(JSONObject obj) {
        try {
           
            JSONArray arrayObj = obj.getJSONArray("data");

            List<Model_Detail_Kuliner> kulinerList = new ArrayList<Model_Detail_Kuliner>();

            for (int i = 0; i < arrayObj.length(); i++) {
                JSONObject kulinerObj = arrayObj.getJSONObject(i);
                Model_Detail_Kuliner kuliner = new Model_Detail_Kuliner();
                
                kuliner.setIdkuliner(kulinerObj.getString("id_kuliner"));
                kuliner.setIdkategori(kulinerObj.getString("id_kategori"));
                kuliner.setFoto(kulinerObj.getString("foto_kuliner"));
                kuliner.setNama_kuliner(kulinerObj.getString("nama_kuliner"));
                kuliner.setKeterangan(kulinerObj.getString("keterangan"));
                kuliner.setLokasi(kulinerObj.getString("lokasi"));

                kulinerList.add(kuliner);
            }
            return kulinerList;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

}