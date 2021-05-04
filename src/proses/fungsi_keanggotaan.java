/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proses;

import java.util.ArrayList;
import master.kriteria;
/**
 *
 * @author imam
 */
public class fungsi_keanggotaan {
    kriteria kriteria;
    public fungsi_keanggotaan(){
        kriteria = new kriteria();
    }
    
    public String[][] get(String nama_kriteria){
        int kolom = 3;
        ArrayList tampung = new ArrayList();
        String[][] data_kriteria = kriteria.get_all2();
        for (String[] dk : data_kriteria) {
            if (nama_kriteria.equals(dk[2])) {
                tampung.add(dk[3]); // rentang
                tampung.add(dk[4]); // min
                tampung.add(dk[5]); // max
            }
        }
        String[][] data = null;
        if(!tampung.isEmpty()){
            data = new String[tampung.size() / kolom][kolom];
            int index = 0;
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = (String) tampung.get(index);
                    index++;
                }
            }
        }
        return data;
    }
    
    public double miuPersediaanSedikit(double persediaan){
        String[][] hrg = get("Persediaan");
        double b1 = 0, b2 = 0;
        for (String[] h : hrg) {
            if ("Sedikit".equals(h[0])) {
                b1 = Double.parseDouble(h[1]);
                b2 = Double.parseDouble(h[2]);
            }
        }
        if (persediaan <= b1) {
            return 1;
        }else if(persediaan > b1 && persediaan < b2){
            return (b2 - persediaan) / (b2 - b1);
        }else{
            return 0;
        }
    }
    
    public double miuPersediaanSedang(double persediaan){
        String[][] hrg = get("Persediaan");
        double b1 = 0, b2 = 0, b3 = 0;
        for (String[] h : hrg) {
            if("Sedikit".equals(h[0])){
                b1 = Double.parseDouble(h[2]);
            }else if("Sedang".equals(h[0])){
                b2 = Double.parseDouble(h[1]);
                b3 = Double.parseDouble(h[2]);
            }
        }
        if (persediaan <= b2 || persediaan >= b3) {
            return 0;
        }else if(persediaan <= b1){
            return (persediaan - b2) / (b1 - b2);
        }else{
            return (b3 - persediaan) / (b3 - b1);
        }
    }
    
    public double miuPersediaanBanyak(double persediaan){
        String[][] hrg = get("Persediaan");
        double b1 = 0, b2 = 0;
        for (String[] h : hrg) {
            if("Banyak".equals(h[0])){
                b1 = Double.parseDouble(h[1]);
                b2 = Double.parseDouble(h[2]);
            }
        }
        if(persediaan <= b1){
            return 0;
        }else if(persediaan > b1 && persediaan < b2){
            return (persediaan - b1) / (b2 - b1);
        }else{
            return 1;
        }
    }
    
    public double miuPermintaanTurun(double permintaan){
        String[][] hrg = get("Permintaan");
        double b1 = 0, b2 = 0;
        for (String[] h : hrg) {
            if ("Turun".equals(h[0])) {
                b1 = Double.parseDouble(h[1]);
                b2 = Double.parseDouble(h[2]);
            }
        }
        if (permintaan <= b1) {
            return 1;
        }else if(permintaan > b1 && permintaan < b2){
            return (b2 - permintaan) / (b2 - b1);
        }else{
            return 0;
        }
    }
    
    public double miuPermintaanSedang(double permintaan){
        String[][] hrg = get("Permintaan");
        double b1 = 0, b2 = 0, b3 = 0;
        for (String[] h : hrg) {
            if("Turun".equals(h[0])){
                b1 = Double.parseDouble(h[2]);
            }else if("Sedang".equals(h[0])){
                b2 = Double.parseDouble(h[1]);
                b3 = Double.parseDouble(h[2]);
            }
        }
        if (permintaan <= b2 || permintaan >= b3) {
            return 0;
        }else if(permintaan <= b1){
            return (permintaan - b2) / (b1 - b2);
        }else{
            return (b3 - permintaan) / (b3 - b1);
        }
    }
    
    public double miuPermintaanNaik(double permintaan){
        String[][] hrg = get("Permintaan");
        double b1 = 0, b2 = 0;
        for (String[] h : hrg) {
            if("Naik".equals(h[0])){
                b1 = Double.parseDouble(h[1]);
                b2 = Double.parseDouble(h[2]);
            }
        }
        if(permintaan <= b1){
            return 0;
        }else if(permintaan > b1 && permintaan < b2){
            return (permintaan - b1) / (b2 - b1);
        }else{
            return 1;
        }
    }
    
    public double produksiKurang(double pre){
        String[][] bintang = get("Produksi");
        double b1 = 0, b2 = 0;
        for (String[] b : bintang) {
            if ("Berkurang".equals(b[0])) {
                b1 = Double.parseDouble(b[1]);
                b2 = Double.parseDouble(b[2]);
            }
        }
        return b2 - pre * (b2 - b1);
    }
    
    public double produksiTambah(double pre){
        String[][] bintang = get("Produksi");
        double b1 = 0, b2 = 0;
        for (String[] b : bintang) {
            if ("Bertambah".equals(b[0])) {
                b1 = Double.parseDouble(b[1]);
                b2 = Double.parseDouble(b[2]);
            }
        }
        return pre * (b2 - b1) + b1;
    }
    
    public static void main(String[] args) {
        fungsi_keanggotaan f = new fungsi_keanggotaan();
        
        System.out.println("Permintaan");
        String[][] data_kriteria = f.get("Permintaan");
        for (int i = 0; i < data_kriteria.length; i++) {
            for (int j = 0; j < data_kriteria[0].length; j++) {
                System.out.print(data_kriteria[i][j]+"; ");
            }
            System.out.println("");
        }
        System.out.println("\nPersediaan");
        data_kriteria = f.get("Persediaan");
        for (int i = 0; i < data_kriteria.length; i++) {
            for (int j = 0; j < data_kriteria[0].length; j++) {
                System.out.print(data_kriteria[i][j]+"; ");
            }
            System.out.println("");
        }
        System.out.println("\nProduksi");
        data_kriteria = f.get("Produksi");
        for (int i = 0; i < data_kriteria.length; i++) {
            for (int j = 0; j < data_kriteria[0].length; j++) {
                System.out.print(data_kriteria[i][j]+"; ");
            }
            System.out.println("");
        }
        System.out.println("");
        
        double permintaan = 1455;
        System.out.println("Data permintaan : "+permintaan);
        System.out.println("Turun : "+f.miuPermintaanTurun(permintaan));
        System.out.println("Sedang: "+f.miuPermintaanSedang(permintaan));
        System.out.println("Naik  : "+f.miuPermintaanNaik(permintaan));
        System.out.println("");
        
        double persediaan = 440;
        System.out.println("Data persediaan : "+persediaan);
        System.out.println("Sedikit : "+f.miuPersediaanSedikit(persediaan));
        System.out.println("Sedang  : "+f.miuPersediaanSedang(persediaan));
        System.out.println("Banyak  : "+f.miuPersediaanBanyak(persediaan));
    }
}
