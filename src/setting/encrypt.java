/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setting;

import java.security.MessageDigest;
/**
 *
 * @author imam
 */
public class encrypt {
    MessageDigest messageDigest;
    
    public String md5(String plaintext){
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(plaintext.getBytes());
        } catch (Exception e) {
            System.out.println(e);
        }
        byte[] output = messageDigest.digest();
        return convert(output);
    }

    private int byte2int(byte data) {
        return data < 0 ? 256 + data : data;
    }

    private String convert(byte[] data) {
        StringBuffer buffer = new StringBuffer();
        for (byte b : data) {
            String s = Integer.toHexString(byte2int(b));
            if (s.length() < 2) {
                buffer.append("0" + s);
            } else {
                buffer.append(s);
            }
        }
        return buffer.toString();
    }
    
//    public static void main(String[] args){
//        encrypt e = new encrypt();
//        String original = "admin";
//        String encryptMD5 = e.md5(original);
// 
//        System.out.println("Kalimat original : "+original);
//        System.out.println("MD5 Hashing : "+encryptMD5);
//    }
}
