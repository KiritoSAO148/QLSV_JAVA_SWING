/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chuanhoa;

/**
 *
 * @author ASUS
 */
public class ChuanHoaTenDangNhap {
    public static String vietThuong (String s){
        String res = "";
        for (int i = 0; i < s.length(); ++i){
            res += Character.toLowerCase(s.charAt(i));
        }
        return res;
    }
    
    public static boolean checkUserName (String s){
        for (int i = 0; i < s.length(); ++i){
            if (!Character.isAlphabetic(s.charAt(i)) && !Character.isDigit(s.charAt(i)) && s.charAt(i) != '_') return false;
        }
        return s.length() >= 8;
    }
}
