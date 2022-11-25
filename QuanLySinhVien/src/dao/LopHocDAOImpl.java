/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.LopHoc;
import java.sql.*;
import java.util.ArrayList;
import static java.util.Collections.list;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public class LopHocDAOImpl implements LopHocDAO {

    List<LopHoc> list = null;

    @Override
    public List<LopHoc> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM lophoc";
            list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHoc lh = new LopHoc();
                lh.setMaLopHoc(rs.getString("ma_lop_hoc"));
                lh.setMaHocPhan(rs.getString("ma_hoc_phan"));
                lh.setMaSinhVien(rs.getString("ma_sinh_vien"));
                lh.setNgayDangKy(rs.getDate("ngay_dang_ky"));
                lh.setStatus(rs.getBoolean("status"));
                list.add(lh);
            }
            ps.close();
            rs.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int createOrUpdate(LopHoc lh) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO lophoc (ma_lop_hoc, ma_hoc_phan, ma_sinh_vien, ngay_dang_ky, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, lh.getMaLopHoc());
            ps.setString(2, lh.getMaHocPhan());
            ps.setString(3, lh.getMaSinhVien());
            ps.setDate(4, new Date(lh.getNgayDangKy().getTime()));
            ps.setBoolean(5, lh.isStatus());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKeys = 0;
            if (rs.next()) {
                generatedKeys = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKeys;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public LopHoc findByMlh(String mlh) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM lophoc WHERE ma_lop_hoc = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, mlh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LopHoc lh = new LopHoc();
                lh.setMaLopHoc(rs.getString("ma_lop_hoc"));
                lh.setMaHocPhan(rs.getString("ma_hoc_phan"));
                lh.setMaSinhVien(rs.getString("ma_sinh_vien"));
                lh.setNgayDangKy(rs.getDate("ngay_dang_ky"));
                lh.setStatus(rs.getBoolean("status"));
                //list.add(lh);
                return lh;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(LopHoc lh) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "UPDATE lophoc SET ma_hoc_phan = ?, ma_sinh_vien = ?, ngay_dang_ky = ?,"
                    + " status = ? WHERE ma_lop_hoc = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(5, lh.getMaLopHoc());
            ps.setString(1, lh.getMaHocPhan());
            ps.setString(2, lh.getMaSinhVien());
            ps.setDate(3, new Date(lh.getNgayDangKy().getTime()));
            ps.setBoolean(4, lh.isStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String mlh) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "DELETE FROM lophoc WHERE ma_lop_hoc = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, mlh);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
