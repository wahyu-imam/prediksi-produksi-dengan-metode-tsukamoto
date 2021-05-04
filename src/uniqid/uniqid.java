/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniqid;

/**
 *
 * @author imam
 */
public class uniqid {
    
    public String get(){
        String data = "";
        for(int i = 0; i <20; i++){ 
            char karakter = uniqid.buatKarakterDigitAngkaAcak(); 
            if((i + 1) % 20 == 0){ 
                data += karakter; 
            }else data += karakter; 
        }
        return data;
    }
    
    private static char buatKarakterDigitAngkaAcak(){ 
        return buatKarakterAcak('0', '9'); 
    }
    
    private static char buatKarakterAcak(char karakter1, char karakter2){ 
        return (char)(karakter1 + Math.random() * (karakter2 - karakter1 + 1)); 
    }
}
