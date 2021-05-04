/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import koneksi.koneksi;
import uniqid.uniqid;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author imam
 */
public class crud {
    Connection con;
    Statement stm;
    ResultSet rs;
    PreparedStatement pst;
    koneksi db;
    uniqid uniqid;
    
    public crud(){
        db = new koneksi();
        uniqid = new uniqid();
    }
    
    protected String[][] getAll(String sql, int kolom, String errorHandling){
        kolom += 1;
        ArrayList tampung = new ArrayList();
        int index = 1;
        try {
            con = db.getKoneksi();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) { 
                tampung.add(index++);
                for (int i = 1; i < kolom; i++) {
                    tampung.add(rs.getString(i));
                }
            }
            con.close(); stm.close(); rs.close();
        } catch (Exception e) {
            System.out.println(errorHandling+" : "+e);
        }
        String[][] data = null;
        if (!tampung.isEmpty()) {
            index = 0;
            data = new String[tampung.size() / kolom][kolom];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = String.valueOf(tampung.get(index++));
                }
            }
        }
        return data;
    }
    
    protected String[] getById(String sql, int kolom, String errorHandling){
        ArrayList tampung = new ArrayList();
        try {
            con = db.getKoneksi();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                for (int i = 1; i <= kolom; i++) {
                    tampung.add(rs.getString(i));
                }
            }
            con.close(); stm.close(); rs.close();
        } catch (Exception e) {
            System.out.println(errorHandling+" : "+e);
        }
        String[] data = null;
        if (!tampung.isEmpty()) {
            data = new String[tampung.size()];
            for (int i = 0; i < data.length; i++) {
                data[i] = String.valueOf(tampung.get(i));
            }
        }
        return data;
    }
    
    protected void simpan(String sql){
        try {
            con = db.getKoneksi();
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close(); pst.close();
            JOptionPane.showMessageDialog(null, "Berhasil simpan data!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal simpan data! "+e);
        }
    }
    
    protected boolean simpanDtl(String sql){
        boolean data;
        try {
            con = db.getKoneksi();
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close(); pst.close();
            data = true;
        } catch (Exception e) {
            data = false;
        }
        return data;
    }
    
    protected void ubah(String sql){
        try {
            con = db.getKoneksi();
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close(); pst.close();
            JOptionPane.showMessageDialog(null, "Berhasil simpan data!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal simpan data! "+e);
        }
    }
    
    protected boolean ubahDtl(String sql){
        boolean data;
        try {
            con = db.getKoneksi();
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close(); pst.close();
            data = true;
        } catch (Exception e) {
            data = false;
        }
        return data;
    }
    
    protected void hapus(String sql){
        try {
            con = db.getKoneksi();
            pst = con.prepareStatement(sql);
            pst.execute();
            con.close(); pst.close();
            JOptionPane.showMessageDialog(null, "Berhasil hapus data!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal hapus data! "+e);
        }
    }
}
