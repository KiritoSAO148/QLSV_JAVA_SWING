/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chuanhoa;

/**
 *
 * @author ASUS
 */
public class ChuanHoaMatKhau {
    public static String vietThuong (String s){
        String res = "";
        for (int i = 0; i < s.length(); ++i){
            res += Character.toLowerCase(s.charAt(i));
        }
        return res;
    }
    
    public static boolean checkPassWord (String s){
        int alpha = 0, digit = 0, special = 0;
        for (int i = 0; i < s.length(); ++i){
            if (Character.isAlphabetic(s.charAt(i))) ++alpha;
            else if (Character.isDigit(s.charAt(i))) ++digit;
            else ++special;
        }
        return s.length() >= 8 && alpha >= 1 && digit >= 1 && special >= 1;
    }
}
