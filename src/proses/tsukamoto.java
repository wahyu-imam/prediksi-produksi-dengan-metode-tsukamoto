/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proses;

import java.text.DecimalFormat;
import java.util.ArrayList;
import master.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import koneksi.koneksi;
import javax.swing.JOptionPane;
import uniqid.uniqid;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import view.*;
/**
 *
 * @author imam
 */
public class tsukamoto {
    rule r;
    produksi p;
    fungsi_keanggotaan f;
    Connection con;
    PreparedStatement pst;
    koneksi db;
    uniqid uniqid;
    Statement stm;
    ResultSet rs;
    DecimalFormat df;
    detailPerhitungan detail;
    menuUtama mu;
    
    public tsukamoto(menuUtama mu, detailPerhitungan detail){
        r = new rule();
        p = new produksi();
        f = new fungsi_keanggotaan();
        db = new koneksi();
        uniqid = new uniqid();
        df = new DecimalFormat("#.##");
        this.mu = mu;
        this.detail = detail;
    }
    
    public tsukamoto(){
        r = new rule();
        p = new produksi();
        f = new fungsi_keanggotaan();
        db = new koneksi();
        uniqid = new uniqid();
        df = new DecimalFormat("#.##");
    }
    
    private void show2D(String[][] data){
        for (String[] data1 : data) {
            for (String d : data1) {
                System.out.print(d+";   ");
            }
            System.out.println("");
        }
    }
    
    public double[][] predikat(double permintaan, double persediaan, String event){
        ArrayList tampung = new ArrayList();
        String[][] data_rule = r.get_all();
        double pmt, psd, pds, evt;
        int kolom = 5;
//        show2D(data_rule);
        for (String[] dr : data_rule) {
            if (dr[4].equals(event)) {
                switch (dr[2]) {
                    case "Turun":
                        pmt = f.miuPermintaanTurun(permintaan);
                        break;
                    case "Sedang":
                        pmt = f.miuPermintaanSedang(permintaan);
                        break;
                    default:
                        pmt = f.miuPermintaanNaik(permintaan);
                        break;
                }

                switch(dr[3]){
                    case "Sedikit":
                        psd = f.miuPersediaanSedikit(persediaan);
                        break;
                    case "Sedang":
                        psd = f.miuPersediaanSedang(persediaan);
                        break;
                    default:
                        psd = f.miuPersediaanBanyak(persediaan);
                        break;
                }

                switch(dr[4]){
                    case "Low":
                        evt = 0;
                        break;
                    default:
                        evt = 1;
                        break;
                }
                double pre;
                pre = Math.min(pmt, psd);

                switch(dr[5]){
                    case "Berkurang":
                        pds = f.produksiKurang(pre);
                        break;
                    default:
                        pds = f.produksiTambah(pre);
                        break;
                }
                tampung.add(pmt);
                tampung.add(psd);
                tampung.add(evt);
                tampung.add(pre);
                tampung.add(pds);
            }
        }
            
        double[][] data = null;
        if(!tampung.isEmpty()){
            data = new double[tampung.size() / kolom][kolom];
            int index = 0;
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = (Double) tampung.get(index);
                    index++;
                }
            }
        }
        return data;
    }
    
    public int z(double[][] predikat){
        double pembilang = 0;
        double penyebut = 0;
        for (int i = 0; i < predikat.length; i++) {
             pembilang += predikat[i][predikat[i].length - 2] * predikat[i][predikat[i].length - 1];
             penyebut += predikat[i][predikat[i].length - 2];
        }
        double z_ = pembilang / penyebut;
        double z = Math.round(pembilang / penyebut);
//        hpt.getTxtHslTsukamto().append("\nz_ : "+z_);
//        hpt.getTxtHslTsukamto().append("\nz : "+z);
//        hpt.getTxtHslTsukamto().append("\n\n");
        return (int) Math.round(pembilang / penyebut);
    }
    
//    public String[][] hitung_masal_tsukamoto(){
//        hpt.getTxtHslTsukamto().setText(null);
//        
//        boolean laporan = true;
//        String[][] data_hotel = h.get_all();
//        String[][] data = new String[data_hotel.length][3];
//        for (int i = 0; i < data_hotel.length; i++) {
////            System.out.println("Data ke-"+(i+1));
//            hpt.getTxtHslTsukamto().append(" -- Data ke-"+(i+1)+" --\n");
//            double harga = Double.parseDouble(data_hotel[i][2]);
//            double kelas = Double.parseDouble(data_hotel[i][3]);
//            double fasilitas = Double.parseDouble(data_hotel[i][4]);
//            double transportasi = Double.parseDouble(data_hotel[i][5]);
//            hpt.getTxtHslTsukamto().append("\nHarga : "+harga);
//            hpt.getTxtHslTsukamto().append("\nKelas : "+kelas);
//            hpt.getTxtHslTsukamto().append("\nFasilitas : "+fasilitas);
//            hpt.getTxtHslTsukamto().append("\nTransportasi : "+transportasi);
//            hpt.getTxtHslTsukamto().append("\n");
//            double[][] data_predikat = predikat(harga, kelas, fasilitas, transportasi);
//            if(laporan){
//                hpt.getTxtHslTsukamto().append("\nKode Hotel\t   Harga\tKelas\tFasilitas\tTransportasi\tPredikat\tZ");
//                hpt.getTxtHslTsukamto().append("\n-----------------------------------------------------------"
//                        + "-------------------------------------------------------------------------------\n");
//                for (int j = 0; j < data_predikat.length; j++) {
//                    hpt.getTxtHslTsukamto().append(df.format(data_predikat[j][0])+"\t   ");
//                    for (int k = 0; k < data_predikat[0].length; k++) {
////                        System.out.print(data_predikat[j][k]+"; ");
//                        if(k != 0){
//                            hpt.getTxtHslTsukamto().append(df.format(data_predikat[j][k])+"\t");
//                        }
//                    }
////                    System.out.println("");
//                    hpt.getTxtHslTsukamto().append("\n");
//                }
//                hpt.getTxtHslTsukamto().append("\n-----------------------------------------------------------"
//                        + "-------------------------------------------------------------------------------\n");
////                System.out.println("");
////                hpt.getTxtHslTsukamto().append("\n");
//            }
//            int z = z(data_predikat);
//            data[i][0] = data_hotel[i][0];
//            data[i][1] = String.valueOf(z);
//            data[i][2] = data_hotel[i][6];
//        }
//        return data;
//    }
    
    public void simpan(String[][] hasil){
        hapus();
        try {
            for (int i = 0; i < hasil.length; i++) {
                String kodeHotel = hasil[i][0];
                String z = hasil[i][1];
                String sql = "INSERT INTO `hasil`(`kode_hasil`, `kode_produksi`, `aktual`) VALUES (?,?,?)";
                con = db.getKoneksi();
                pst = con.prepareStatement(sql);
                pst.setString(1, uniqid.get());
                pst.setString(2, kodeHotel);
                pst.setString(3, z);
                pst.execute();
                con.close(); pst.close();
            }
//            JOptionPane.showMessageDialog(null, "Berhasil simpan data");
        } catch (Exception e) {
            System.out.println("error : "+e);
        }
    }
    
    public void hapus(){
        String sql = "DELETE FROM `hasil`";
        try {
            con = db.getKoneksi();
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close(); pst.close();
        } catch (Exception e) {
            System.out.println("error : "+e);
        }
    }
    
    public String[][] hitungSemua(){
        mu.getTxtHasilProses().setText(null);
        detail.getTxtDetailProses().setText(null);
        boolean report = true;
        String[][] data_produksi = p.get_all2();
        String[][] data = new String[data_produksi.length][2];
//        show2D(data_produksi);
//        System.out.println("\n\n");
        for (int i = 0; i < data_produksi.length; i++) {
            double permintaan = Double.valueOf(data_produksi[i][3]);
            double persediaan = Double.valueOf(data_produksi[i][4]);
            String event = data_produksi[i][5];
            double[][] predikat = predikat(permintaan,persediaan, event);
            int z = z(predikat);
            if (report) {
                detail.getTxtDetailProses().append("Data ke["+(i+1)+"]\n\n");
                detail.getTxtDetailProses().append("Permintaan : "+permintaan+"\n");
                detail.getTxtDetailProses().append("Persediaan : "+persediaan+"\n");
                detail.getTxtDetailProses().append("Event      : "+event+"\n\n");
                detail.getTxtDetailProses().append("Permintaan;  Persediaan;  Event;  Predikat;  z\n");
                detail.getTxtDetailProses().append("------------------------------------------------------------------\n");
                for (int j = 0; j < predikat.length; j++) {
                    for (int k = 0; k < predikat[j].length; k++) {
                        if (k == predikat[k].length - 3) {
                            if (predikat[j][k] == 0) {
//                                System.out.print("Low;  ");
                                detail.getTxtDetailProses().append("Low;    ");
                            }else{
//                                System.out.print("High; ");
                                detail.getTxtDetailProses().append("High;   ");
                            }
                        }else{
//                            System.out.print(predikat[j][k]+";   ");
                            detail.getTxtDetailProses().append(predikat[j][k]+";   ");
                        }
                    }
//                    System.out.println("");
                    detail.getTxtDetailProses().append("\n");
                }
                detail.getTxtDetailProses().append("------------------------------------------------------------------\n");
                detail.getTxtDetailProses().append("\nData ke["+(i+1)+"]\n\n");
                detail.getTxtDetailProses().append("Bulan  : "+data_produksi[i][2]+"\n");
                detail.getTxtDetailProses().append("Aktual : "+z+"\n");
                detail.getTxtDetailProses().append("Target : "+data_produksi[i][data_produksi[i].length - 1]+"\n\n");
                
                mu.getTxtHasilProses().append("Data ke["+(i+1)+"]\n\n");
                mu.getTxtHasilProses().append("Bulan  : "+data_produksi[i][2]+"\n");
                mu.getTxtHasilProses().append("Aktual : "+z+"\n");
                mu.getTxtHasilProses().append("Target : "+data_produksi[i][data_produksi[i].length - 1]+"\n\n");
            }
//            System.out.print("Bulan : "+data_produksi[i][2]);
//            System.out.print("  Aktual : "+z);
//            System.out.println("    Target : "+data_produksi[i][data_produksi[i].length - 1]);
            data[i][0] = data_produksi[i][1]; //kode produksi
            data[i][1] = String.valueOf(z); // aktual
        }
        return data;
    }
    
    public void hitung(String bulan, double permintaan, double persediaan, String event){
        mu.getTxtHasilProses().setText(null);
        detail.getTxtDetailProses().setText(null);
        double[][] predikat = predikat(permintaan,persediaan, event);
//        boolean report = true;
        detail.getTxtDetailProses().append("Permintaan;  Persediaan;  Event;  Predikat;  z\n");
        detail.getTxtDetailProses().append("------------------------------------------------------------------\n");
        for (int i = 0; i < predikat.length; i++) {
            for (int j = 0; j < predikat[i].length; j++) {
                if (j == predikat[i].length - 3) {
                    if (predikat[i][j] == 0) {
//                        System.out.print("Low;  ");
                        detail.getTxtDetailProses().append("Low;    ");
                    }else{
//                        System.out.print("High; ");
                        detail.getTxtDetailProses().append("High;   ");
                    }
                }else{
//                    System.out.print(predikat[i][j]+";   ");
                    detail.getTxtDetailProses().append(predikat[i][j]+";   ");
                }
            }
//            System.out.println("");
            detail.getTxtDetailProses().append("\n");
        }
        detail.getTxtDetailProses().append("------------------------------------------------------------------\n");
        int z = z(predikat);
//        System.out.println("Hasil : "+z);
        mu.getTxtHasilProses().append("Produksi yang optimal adalah : "+z);
//        String kode_produksi = uniqid.get();
//        String[][] dataProduksi = {
//            {"kode_produksi", kode_produksi},
//            {"bln", bulan},
//            {"permintaan", String.valueOf(permintaan)},
//            {"persediaan", String.valueOf(persediaan)},
//            {"event", event},
//            {"produksi", String.valueOf(z)}
//        };
//        p.save(dataProduksi);
//        String[][] data = new String[1][2];
//        data[0][0] = kode_produksi;
//        data[0][1] = z;
    }
    
//    public static void main(String[] args) {
//        DecimalFormat df = new DecimalFormat("#.##");
//        tsukamoto ts = new tsukamoto();
//        master.rule r = new rule();
//        master.hotel h = new hotel();
//        master.kriteria k = new kriteria();
//        
////        String[][] data_rule = r.get_all();
////        for (int i = 0; i < data_rule.length; i++) {
////            for (int j = 0; j < data_rule[i].length; j++) {
////                System.out.print(data_rule[i][j]+"; ");
////            }
////            System.out.println("");
////        }
////        System.out.println("");
////        
////        String[][] data_kriteria = k.get_all();
////        for (int i = 0; i < data_kriteria.length; i++) {
////            for (int j = 0; j < data_kriteria[i].length; j++) {
////                System.out.print(data_kriteria[i][j]+"; ");
////            }
////            System.out.println("");
////        }
////        System.out.println("");
////        String[][] data_hotel = h.get_all();
////        for (int i = 0; i < data_hotel.length; i++) {
////            for (int j = 0; j < data_hotel[i].length; j++) {
////                System.out.print(data_hotel[i][j]+"; ");
////            }
////            System.out.println("");
////        }
////        //243749; 4; 9; 3; 4;
////        double harga = 378000;
////        double kelas = 2;
////        double fasilitas = 9;
////        double transportasi = 9;
////        
////        double[][] data_predikat = ts.predikat(harga, kelas, fasilitas, transportasi);
////        int z = ts.z(data_predikat);
////        for (int i = 0; i < data_predikat.length; i++) {
////            for (int j = 0; j < data_predikat[0].length; j++) {
////                System.out.print(df.format(data_predikat[i][j])+"; ");
////            }
////            System.out.println("");
////        }
////        System.out.println("");
////        System.out.println("hasil: "+ z);
//        String[][] data_tsukamoto = ts.hitung_masal_tsukamoto();
//        int sama = 0;
//        for (String[] ht : data_tsukamoto) {
//            if (ht[1].equals(ht[2])) {
//                sama++;
//            }
//            System.out.println(ht[0]+"; "+ht[1]+"; "+ht[2]);
//        }
//        System.out.println("\n Data Sama : "+sama);
//    }
    public static void main(String[] args) {
        tsukamoto t = new tsukamoto();//234
//        produksi p = new produksi();
//        String[][] data_produksi = p.get_all2();
////        t.show2D(data_produksi);
//        System.out.println("\n\n");
//        for (int i = 0; i < data_produksi.length; i++) {
//            double permintaan = Double.valueOf(data_produksi[i][3]);
//            double persediaan = Double.valueOf(data_produksi[i][4]);
//            String event = data_produksi[i][5];
//            double[][] predikat = t.predikat(permintaan,persediaan, event);
//            
//            int z = t.z(predikat);
//            System.out.print("Bulan : "+data_produksi[i][2]);
//            System.out.print("  Aktual : "+z);
//            System.out.println("    Target : "+data_produksi[i][data_produksi[i].length - 1]);
//        }
//        double[][] predikat = t.predikat(930,250, "Low");
//        for (int i = 0; i < predikat.length; i++) {
//            for (int j = 0; j < predikat[i].length; j++) {
//                if (j == predikat[i].length - 3) {
//                    if (predikat[i][j] == 0) {
//                        System.out.print("Low;  ");
//                    }else{
//                        System.out.print("High; ");
//                    }
//                }else{
//                    System.out.print(predikat[i][j]+";   ");
//                }
//            }
//            System.out.println("");
//        }
//        int z = t.z(predikat);
//        System.out.println("Hasil : "+z);
        t.hitungSemua();
    }
}