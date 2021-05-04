/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import crud.crud;
import setting.query;
/**
 *
 * @author imam
 */
public class rule extends crud{
    query db;
    
    public rule(){
        db = new query();
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
        String sql = db.select("*");
        sql += db.from("rule");
        String[][] data = getAll(sql, 5, "Error get_all rule");
        return data;
    }
    
    public String[][] getRule(String event){
        String sql = db.select("permintaan, persediaan, event, produksi");
        sql += db.from("rule");
        sql += db.where("event", event);
        String[][] data = getAll(sql, 4, "Error getRule rule");
        return data;
    }
    
    public void save(String[][] data){
        String sql = db.insert("rule", data);
        simpan(sql);
    }
    
    public void edit(String[][] data, String kode){
        String sql = db.update("rule", data);
        sql += db.where("kode_rule", kode);
        ubah(sql);
    }
    
    public void delete(String kode){
        String sql = db.delete("rule");
        sql += db.where("kode_rule", kode);
        hapus(sql);
    }
    
    public static void main(String[] args) {
        rule r = new rule();
//        r.save("Turun", "Sedikit", "Low", "Berkurang");
//        r.save("Turun", "Sedang", "Low", "Berkurang");
//        r.save("Turun", "Banyak", "Low", "Berkurang");
//        r.save("Sedang", "Sedikit", "Low", "Bertambah");
//        r.save("Sedang", "Sedang", "Low", "Berkurang");
//        r.save("Sedang", "Banyak", "Low", "Berkurang");
//        r.save("Naik", "Sedikit", "Low", "Bertambah");
//        r.save("Naik", "Sedang", "Low", "Bertambah");
//        r.save("Naik", "Banyak", "Low", "Bertambah");
//        r.save("Turun", "Sedikit", "Hight", "Berkurang");
//        r.save("Turun", "Sedang", "Hight", "Berkurang");
//        r.save("Turun", "Banyak", "Hight", "Berkurang");
//        r.save("Sedang", "Sedikit", "Hight", "Bertambah");
//        r.save("Sedang", "Sedang", "Hight", "Bertambah");
//        r.save("Sedang", "Banyak", "Hight", "Bertambah");
//        r.save("Naik", "Sedikit", "Hight", "Bertambah");
//        r.save("Naik", "Sedang", "Hight", "Bertambah");
//        r.save("Naik", "Banyak", "Hight", "Bertambah");
        String[][] data_rule = r.get_all();
        r.show2D(data_rule);
    }
}
