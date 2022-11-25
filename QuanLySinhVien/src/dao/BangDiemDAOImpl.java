/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.BangDiem;
import model.HocPhan;

/**
 *
 * @author ASUS
 */
public class BangDiemDAOImpl implements BangDiemDAO{
    
    List<BangDiem> list = null;

    @Override
    public List<BangDiem> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM bangdiem";
            list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BangDiem bd = new BangDiem();
                bd.setMaSinhVien(rs.getString("ma_sinh_vien"));
                bd.setTenSinhVien(rs.getString("ho_ten"));
                bd.setMaHocPhan(rs.getString("ma_hoc_phan"));
                bd.setTenHocPhan(rs.getString("ten_hoc_phan"));
                bd.setSoTinChi(rs.getInt("so_tin_chi"));
                bd.setDiemcc(Double.parseDouble(rs.getString("diem_cc")));
                bd.setDiemkt(Double.parseDouble(rs.getString("diem_kt")));
                bd.setDiemth(Double.parseDouble(rs.getString("diem_th")));
                bd.setDiembt(Double.parseDouble(rs.getString("diem_bt")));
                bd.setEnd(Double.parseDouble(rs.getString("thi_cuoi_ky")));
                list.add(bd);
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
    public int createOrUpdate(BangDiem bd) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO bangdiem (ma_sinh_vien, ho_ten, ma_hoc_phan, ten_hoc_phan, so_tin_chi, diem_cc, diem_kt, diem_th, diem_bt, thi_cuoi_ky) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, bd.getMaSinhVien());
            ps.setString(2, bd.getTenSinhVien());
            ps.setString(3, bd.getMaHocPhan());
            ps.setString(4, bd.getTenHocPhan());
            ps.setInt(5, bd.getSoTinChi());
            ps.setString(6, bd.getDiemcc() + "");
            ps.setString(7, bd.getDiemkt() + "");
            ps.setString(8, bd.getDiemth() + "");
            ps.setString(9, bd.getDiembt() + "");
            ps.setString(10, bd.getEnd()+ "");
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
    public BangDiem findByMsv(String msv) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM bangdiem WHERE ma_sinh_vien = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, msv);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BangDiem bd = new BangDiem();
                bd.setMaSinhVien(rs.getString("ma_sinh_vien"));
                bd.setTenSinhVien(rs.getString("ho_ten"));
                bd.setMaHocPhan(rs.getString("ma_hoc_phan"));
                bd.setTenHocPhan(rs.getString("ten_hoc_phan"));
                bd.setSoTinChi(rs.getInt("so_tin_chi"));
                bd.setDiemcc(Double.parseDouble(rs.getString("diem_cc")));
                bd.setDiemkt(Double.parseDouble(rs.getString("diem_kt")));
                bd.setDiemth(Double.parseDouble(rs.getString("diem_th")));
                bd.setDiembt(Double.parseDouble(rs.getString("diem_bt")));
                bd.setEnd(Double.parseDouble(rs.getString("thi_cuoi_ky")));
                //list.add(bd);
                return bd;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(BangDiem bd) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "UPDATE bangdiem SET ho_ten = ?, ma_hoc_phan = ?, ten_hoc_phan = ?, so_tin_chi = ?, diem_cc = ?, diem_kt = ?, diem_th = ?, diem_bt = ?, thi_cuoi_ky = ? WHERE ma_sinh_vien = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(10, bd.getMaSinhVien());
            ps.setString(1, bd.getTenSinhVien());
            ps.setString(2, bd.getMaHocPhan());
            ps.setString(3, bd.getTenHocPhan());
            ps.setInt(4, bd.getSoTinChi());
            ps.setString(5, bd.getDiemcc() + "");
            ps.setString(6, bd.getDiemkt()+ "");
            ps.setString(7, bd.getDiemth()+ "");
            ps.setString(8, bd.getDiembt() + "");
            ps.setString(9, bd.getEnd()+ "");
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
            String sql = "DELETE FROM bangdiem WHERE ma_sinh_vien = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, msv);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
}
