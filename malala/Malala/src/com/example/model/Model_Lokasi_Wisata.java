package com.example.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Model_Lokasi_Wisata {
	
	private String idwisata;
	private String idkabupaten;
	private String	nama;
	private String	alamat;
	private String	gambar;
	private Double	lat;
	private Double	lng;
	
	public String getIdwisata() {
		return idwisata;
	}
	public void setIdwisata(String idwisata) {
		this.idwisata = idwisata;
	}
	
	
	public String getIdkabupaten(){
		return idkabupaten;
	}
	public void setIdkabupaten (String idkabupaten) {
		this.idkabupaten = idkabupaten;
	}
	
	public String getGambar()
	{
		return gambar;
	}

	public void setGambar(String gambar)
	{
		this.gambar = gambar;
	}

	public String getNama_wisata()
	{
		return nama;
	}

	public void setNama_wisata(String nama)
	{
		this.nama = nama;
	}

	public String getAlamat()
	{
		return alamat;
	}

	public void setAlamat(String alamat)
	{
		this.alamat = alamat;
	}

	public Double getLat()
	{
		return lat;
	}

	public void setLat(Double lat)
	{
		this.lat = lat;
	}

	public Double getLng()
	{
		return lng;
	}

	public void setLng(Double lng)
	{
		this.lng = lng;
	}

	public static List<Model_Lokasi_Wisata> parseFeed(String response) {

		try {
			JSONObject object = new JSONObject(response);
			return parseFeed (object);

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Model_Lokasi_Wisata> parseFeed(JSONObject obj) {
        try {
           
            JSONArray arrayObj = obj.getJSONArray("data");

            List<Model_Lokasi_Wisata> lokasiList = new ArrayList<Model_Lokasi_Wisata>();

            for (int i = 0; i < arrayObj.length(); i++) {
                JSONObject wisataObj = arrayObj.getJSONObject(i);
                Model_Lokasi_Wisata lokasi = new Model_Lokasi_Wisata();
                
                lokasi.setIdwisata(wisataObj.getString("id_wisata"));
                lokasi.setIdkabupaten(wisataObj.getString("idk"));
                lokasi.setNama_wisata(wisataObj.getString("nama_tempat_wisata"));
                lokasi.setLat       (wisataObj.getDouble("lat"));
                lokasi.setLng(wisataObj.getDouble("long"));
                lokasi.setGambar(wisataObj.getString("foto"));
               

                lokasiList.add(lokasi);
            }
            return lokasiList;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

}


