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
public class produksi extends crud{
    query db;
    
    public produksi(){
        db = new query();
    }
    
    private void show2D(String[][] data){
        for (String[] data1 : data) {
            for (String d : data1) {
                System.out.print(d+"; ");
            }
            System.out.println("");
        }
    }
    
    public String autoCode(){
        String sql = db.select(db.max("kode_produksi"));
        sql += db.from("produksi");
        String[] data = getById(sql, 1, "Error autoCode produksi");
        String kode = data[0];
        if (kode.equals("null")) {
            kode = "P001";
        }else{
            kode = kode.replace("P", "");
            int urut = Integer.parseInt(kode);
            urut++;
            if (urut < 10) {
                kode = "P00"+urut;
            }else if (urut >= 10 && urut < 100) {
                kode = "P0"+urut;
            }else if (urut >= 100 && urut < 1000) {
                kode = "P"+urut;
            }
        }
        return kode;
    }
    
    public String[][] get_all(){
        String sql = db.select("kode_produksi, bln, "+db.format("permintaan", 0)+", "+db.format("persediaan", 0)+
                ", event, "+db.format("produksi", 0));
        sql += db.from("produksi");
        String[][] data = getAll(sql, 6, "Error get_all produksi");
        return data;
    }
    
    public String[][] get_all2(){
        String sql = db.select("*");
        sql += db.from("produksi");
        String[][] data = getAll(sql, 6, "Error get_all2 produksi");
        return data;
    }
    
    public String[][] getAllRelasiHasil(){
        String[] field = {"bln","permintaan","persediaan","event","produksi","aktual"};
        String sql = db.select(field);
        sql += db.from("produksi");
        sql += db.join("hasil", "hasil.kode_produksi=produksi.kode_produksi");
        sql += db.order_by("produksi.kode_produksi", "ASC");
        String[][] data = getAll(sql, 6, "Error getAllRelasiHasil produksi");
        return data;
    }
    
    public void save(String[][] data){
        String sql = db.insert("produksi", data);
        simpan(sql);
    }
    
    public void edit(String[][] data, String kode){
        String sql = db.update("produksi", data);
        sql += db.where("kode_produksi", kode);
        ubah(sql);
    }
    
    public void delete(String kode){
        String[] field = {"kode_produksi", kode};
        String sql = db.delete("produksi", field);
        hapus(sql);
    }
    
    public double[] minMax(){
        String sql = db.select(db.min("permintaan")+", "+db.max("permintaan")+", "+
                db.min("persediaan")+", "+db.max("persediaan")+", "+
                db.min("produksi")+", "+db.max("produksi"));
        sql += db.from("produksi");
        String[] minMax = getById(sql, 6, "Error minMax produksi");
        double[] data = new double[minMax.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = Double.parseDouble(minMax[i]);
        }
        return data;
    }
    
    public static void main(String[] args) {
        produksi p = new produksi();
//        p.save("Januari 2019", 1455, 440, 1660);
//        p.save("Februari 2019", 930, 250, 1100);
//        p.save("Maret 2019", 520, 370, 615);
//        p.save("April 2019", 755, 245, 730);
//        p.save("Mei 2019", 1780, 790, 1235);
//        p.save("Juni 2019", 1625, 630, 1720);
//        p.save("Juli 2019", 1320, 525, 1410);
//        p.save("Agustus 2019", 885, 230, 975);
//        p.save("September 2019", 620, 190, 700);
//        p.save("Oktober 2019", 975, 445, 1105);
//        p.save("November 2019", 1620, 710, 1785);
//        p.save("Desember 2019", 1710, 785, 1430);
//        p.save("Januari 2020", 1515, 690, 1045);
//        p.save("Februari 2020", 1030, 615, 925);
//        p.save("Maret 2020", 625, 170, 850);
//        p.save("April 2020", 420, 55, 645);
//        p.save("Mei 2020", 490, 70, 770);
//        p.save("Juni 2020", 670, 115, 665);
//        p.save("Juli 2020", 530, 180, 730);
//        p.save("Agustus 2020", 475, 60, 615);
//        p.save("September 2020", 725, 230, 805);
//        p.save("Oktober 2020", 1080, 660, 1350);
//        p.save("November 2020", 1715, 725, 1680);
//        p.save("Desember 2020", 1685, 780, 1415);
//        p.save("Januari 2021", 1320, 435, 995);
//        p.save("Februari 2021", 1210, 595, 880);
//        String[][] produksi = p.get_all();
        String kode = p.autoCode();
        System.out.println(kode);
    }
}
