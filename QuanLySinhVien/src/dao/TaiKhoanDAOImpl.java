/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import chuanhoa.ChuanHoaMatKhau;
import chuanhoa.ChuanHoaTenDangNhap;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TaiKhoan;

/**
 *
 * @author ASUS
 */
public class TaiKhoanDAOImpl implements TaiKhoanDAO{

    @Override
    public TaiKhoan login(String tdn, String mk) {
        TaiKhoan taikhoan = null;
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM tai_khoan WHERE ten_dang_nhap LIKE ? AND mat_khau LIKE ?";
            
            PreparedStatement ps = cons.prepareStatement(sql);
            String username = ChuanHoaTenDangNhap.vietThuong(tdn);
            String password = ChuanHoaMatKhau.vietThuong(mk);
            ps.setString(1, username);
            ps.setString(2, mk);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                taikhoan = new TaiKhoan();
                taikhoan.setMa_tai_khoan(rs.getInt("ma_tai_khoan"));
                taikhoan.setTen_dang_nhap(rs.getString("ten_dang_nhap"));
                taikhoan.setMat_khau(rs.getString("mat_khau"));
                taikhoan.setTinh_trang(rs.getBoolean("tinh_trang"));
            }
            ps.close();
            cons.close();
            return taikhoan;
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        return null;
    }
    
}
