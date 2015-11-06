package com.example.model;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Model_Kuliner{
	
	private String idkuliner;
    private String nama_kuliner;
    private String foto;
    private String info;
    private String idkategori;
   
    
   
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
	public String getIdkategori() {
		return idkategori;
	}
	public void setIdkategori(String idkategori) {
		this.idkategori = idkategori;
		
	}
	
	public String getIdkuliner() {
		return idkuliner;
	}
	public void setIdkuliner(String idkuliner) {
		this.idkuliner = idkuliner;
	}
	
	public String getInfo(){
		return info;
	}
	public void setInfo(String info){
		this.info = info;
	}
	
	public static List<Model_Kuliner> parseFeed(String response) {

		try {
			JSONObject object = new JSONObject(response);
			return parseFeed (object);

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Model_Kuliner> parseFeed(JSONObject obj) {
        try {
           
            JSONArray arrayObj = obj.getJSONArray("data");

            List<Model_Kuliner> kulinerList = new ArrayList<Model_Kuliner>();

            for (int i = 0; i < arrayObj.length(); i++) {
                JSONObject kulinerObj = arrayObj.getJSONObject(i);
                Model_Kuliner kuliner = new Model_Kuliner();
                
                kuliner .setIdkuliner (kulinerObj.getString("id_kuliner"));
                kuliner.setIdkategori (kulinerObj.getString("id_kategori"));
                kuliner .setNama_kuliner(kulinerObj.getString("nama_kuliner"));
                kuliner .setFoto(kulinerObj.getString("foto_kuliner"));
                kuliner .setInfo(kulinerObj.getString("keterangan"));
              
                kulinerList.add(kuliner);
            }
            return kulinerList;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

}