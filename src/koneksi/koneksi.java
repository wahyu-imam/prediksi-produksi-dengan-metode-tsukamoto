/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author imam
 */
public class koneksi {
    public static Connection con;
    
    public Connection getKoneksi(){
        try {
            String url ="jdbc:mysql://localhost/tsukamoto";
            String user="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection(url,user,pass);
//            System.out.println("koneksi berhasil");
        } catch (Exception e) {
            System.err.println("koneksi gagal" +e.getMessage());
        }
        return con;
    }
    
    public static void main(String[] args) {
        koneksi kon = new koneksi();
        kon.getKoneksi();
    }
}
