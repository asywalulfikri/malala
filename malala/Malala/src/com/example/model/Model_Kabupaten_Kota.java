package com.example.model;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Model_Kabupaten_Kota{
	
	private String idkabupaten;
    private String nama_kabupaten;
    private String foto;
    
    
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getNama_kabupaten() {
		return nama_kabupaten;
	}
	public void setNama_kabupaten(String nama_kabupaten) {
		this.nama_kabupaten = nama_kabupaten;
	}
	
	
	public String getIdkabupaten() {
		return idkabupaten;
	}
	public void setIdkabupaten(String idkabupaten) {
		this.idkabupaten = idkabupaten;
	}
	
	public static List<Model_Kabupaten_Kota> parseFeed(String response) {

		try {
			JSONObject object = new JSONObject(response);
			return parseFeed (object);

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Model_Kabupaten_Kota> parseFeed(JSONObject obj) {
        try {
           
            JSONArray arrayObj = obj.getJSONArray("data");

            List<Model_Kabupaten_Kota> kabupatenList = new ArrayList<Model_Kabupaten_Kota>();

            for (int i = 0; i < arrayObj.length(); i++) {
                JSONObject tempatwisataObj = arrayObj.getJSONObject(i);
                Model_Kabupaten_Kota tempatwisata = new Model_Kabupaten_Kota();
                
                tempatwisata.setIdkabupaten(tempatwisataObj.getString("idk"));
                tempatwisata.setNama_kabupaten(tempatwisataObj.getString("nama_kabupaten"));
                tempatwisata.setFoto(tempatwisataObj.getString("foto"));
               

                kabupatenList.add(tempatwisata);
            }
            return kabupatenList;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

}