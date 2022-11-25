/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.SinhVien;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class SinhVienDAOImpl implements SinhVienDAO{
    List <SinhVien> list = null;
    
    @Override
    public List<SinhVien> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM sinhvien";
            list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                SinhVien sv = new SinhVien();
                sv.setMaSinhVien(rs.getString("ma_sinh_vien"));
                sv.setHoTen(rs.getString("ho_ten"));
                sv.setNgaySinh(rs.getDate("ngay_sinh"));
                sv.setGioiTinh(rs.getBoolean("gioi_tinh"));
                sv.setSoDienThoai(rs.getString("so_dien_thoai"));
                sv.setDiaChi(rs.getString("dia_chi"));
                sv.setNganh(rs.getString("nganh"));
                sv.setGpa(rs.getString("gpa"));
                sv.setStatus(rs.getBoolean("status"));
                list.add(sv);
            }
            ps.close();
            rs.close();
            cons.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public int createOrUpdate (SinhVien sv){
        try{
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO sinhvien (ma_sinh_vien, ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, dia_chi, nganh, gpa, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, sv.getMaSinhVien());
            ps.setString(2, sv.getHoTen());
            ps.setDate(3, new Date(sv.getNgaySinh().getTime()));
            ps.setBoolean(4, sv.getGioiTinh());
            ps.setString(5, sv.getSoDienThoai());
            ps.setString(6, sv.getDiaChi());
            ps.setString(7, sv.getNganh());
            ps.setString(8, sv.getGpa());
            ps.setBoolean(9, sv.getStatus());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKeys = 0;
            if (rs.next()){
                generatedKeys = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKeys;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public static void main(String[] args) {
        SinhVienDAO sinhVienDAO = new SinhVienDAOImpl();
        System.out.println(sinhVienDAO.getList());
    }

    @Override
    public SinhVien findByMsv(String msv) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM sinhvien WHERE ma_sinh_vien = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, msv);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                SinhVien sv = new SinhVien();
                sv.setMaSinhVien(rs.getString("ma_sinh_vien"));
                sv.setHoTen(rs.getString("ho_ten"));
                sv.setNgaySinh(rs.getDate("ngay_sinh"));
                sv.setGioiTinh(rs.getBoolean("gioi_tinh"));
                sv.setSoDienThoai(rs.getString("so_dien_thoai"));
                sv.setDiaChi(rs.getString("dia_chi"));
                sv.setNganh(rs.getString("nganh"));
                sv.setGpa(rs.getString("gpa"));
                sv.setStatus(rs.getBoolean("status"));
                //list.add(sv);
                return sv;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(SinhVien sv) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "UPDATE sinhvien SET ho_ten = ?, ngay_sinh = ?, gioi_tinh = ?, so_dien_thoai = ?,"
                    + " dia_chi = ?, nganh = ?, gpa = ?, status = ? WHERE ma_sinh_vien = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(9, sv.getMaSinhVien());
            ps.setString(1, sv.getHoTen());
            ps.setDate(2, new Date(sv.getNgaySinh().getTime()));
            ps.setBoolean(3, sv.getGioiTinh());
            ps.setString(4, sv.getSoDienThoai());
            ps.setString(5, sv.getDiaChi());
            ps.setString(6, sv.getNganh());
            ps.setString(7, sv.getGpa());
            ps.setBoolean(8, sv.getStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String msv) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "DELETE FROM sinhvien WHERE ma_sinh_vien = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, msv);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return false;
    }

    
}
