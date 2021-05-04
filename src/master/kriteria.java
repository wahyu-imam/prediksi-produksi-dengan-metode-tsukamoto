/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import setting.query;
import crud.crud;
/**
 *
 * @author imam
 */
public class kriteria extends crud{
    query db;
    produksi produksi;
    
    public kriteria(){
        db = new query();
        produksi = new produksi();
    }
    
    private void show2D(String[][] data){
        for (String[] data1 : data) {
            for (String d : data1) {
                System.out.print(d+";   ");
            }
            System.out.println("");
        }
    }
    
    public String[][] get_all(){
        String sql = db.select("kode_kriteria, nama_kriteria, rentang, "+db.format("min", 0)+", "+db.format("max", 0));
        sql += db.from("kriteria");
        String[][] data = getAll(sql, 5, "Error get_all kriteria");
        return data;
    }
    
    public String[][] get_all2(){
        String sql = db.select("*");
        sql += db.from("kriteria");
        String[][] data = getAll(sql, 5, "Error get_all2 kriteria");
        return data;
    }
    
    public String[][] getKriteria(){
        String sql = db.select("nama_kriteria, rentang, min, max");
        sql += db.from("kriteria");
        String[][] data = getAll(sql, 4, "Error getKriteria kriteria");
        return data;
    }
    
    public void save(String[][] data){
        String sql = db.insert("kriteria", data);
        simpan(sql);
    }
    
    public void edit(String[][] data, String kode){
        String sql = db.update("kriteria", data);
        sql += db.where("kode_kriteria", kode);
        ubah(sql);
    }
    
    public void delete(String kode){
        String[] field = {"kode_kriteria", kode};
        String sql = db.delete("kriteria", field);
        hapus(sql);
    }
    
    public double getNilai(String namaKriteria, String rentang){
        double[]minMax = produksi.minMax();
        double data = 0;
        
        switch(namaKriteria){
            case "Permintaan":
                if (rentang.equals("Turun")) {
                    data = minMax[0];
                }else if(rentang.equals("Naik")){
                    data = minMax[1];
                }
                break;
            case "Persediaan":
                if (rentang.equals("Sedikit")) {
                    data = minMax[2];
                }else if(rentang.equals("Banyak")){
                    data = minMax[3];
                }
                break;
            case "Produksi":
                if (rentang.equals("Berkurang")) {
                    data = minMax[4];
                }else if(rentang.equals("Bertambah")){
                    data = minMax[5];
                }
                break;
        }
        return data;
    }
}
