/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setting;

import java.util.Locale;
import java.util.StringTokenizer;
import java.text.NumberFormat;
/**
 *
 * @author imam
 */
public class formatMataUang {
    
    public String formatIndonesia(String harga){
        StringTokenizer token;
        String ganti = "";
        long angka;
        harga = harga.replace(".", "");
        angka = Long.parseLong(harga);
        ganti = NumberFormat.getNumberInstance(Locale.US).format(angka);
        token = new StringTokenizer(ganti, ".");
        ganti = token.nextToken();
        ganti = ganti.replace(',', '.');
        String data = String.format(ganti);
        return data;
    }
    
    public String reFormatIndonesia(String harga){
        String data = harga.replace(".", "");
        data = data.replace(",", "");
        return data;
    }
}
