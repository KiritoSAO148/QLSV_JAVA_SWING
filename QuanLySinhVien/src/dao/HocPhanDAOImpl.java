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
import model.HocPhan;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public class HocPhanDAOImpl implements HocPhanDAO {

    List<HocPhan> list = null;

    @Override
    public List<HocPhan> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM hocphan";
            list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocPhan hp = new HocPhan();
                hp.setMaHocPhan(rs.getString("ma_hoc_phan"));
                hp.setTenHocPhan(rs.getString("ten_hoc_phan"));
                hp.setMoTa(rs.getString("mo_ta"));
                hp.setSoTinChi(rs.getInt("so_tin_chi"));
                hp.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                hp.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                hp.setStatus(rs.getBoolean("tinh_trang"));
                list.add(hp);
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
    public int createOrUpdate(HocPhan hp) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO hocphan (ma_hoc_phan, ten_hoc_phan, mo_ta, so_tin_chi, ngay_bat_dau, ngay_ket_thuc, tinh_trang) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, hp.getMaHocPhan());
            ps.setString(2, hp.getTenHocPhan());
            ps.setString(3, hp.getMoTa());
            ps.setInt(4, hp.getSoTinChi());
            ps.setDate(5, new Date(hp.getNgayBatDau().getTime()));
            ps.setDate(6, new Date(hp.getNgayKetThuc().getTime()));
            ps.setBoolean(7, hp.isStatus());
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
    public HocPhan findByMhp(String mhp) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM hocphan WHERE ma_hoc_phan = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, mhp);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HocPhan hp = new HocPhan();
                hp.setMaHocPhan(rs.getString("ma_hoc_phan"));
                hp.setTenHocPhan(rs.getString("ten_hoc_phan"));
                hp.setMoTa(rs.getString("mo_ta"));
                hp.setSoTinChi(rs.getInt("so_tin_chi"));
                hp.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                hp.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                hp.setStatus(rs.getBoolean("tinh_trang"));
                //list.add(hp);
                return hp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(HocPhan hp) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "UPDATE hocphan SET ten_hoc_phan = ?, mo_ta = ?, so_tin_chi = ?, ngay_bat_dau = ?, ngay_ket_thuc = ?, tinh_trang = ? WHERE ma_hoc_phan = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(7, hp.getMaHocPhan());
            ps.setString(1, hp.getTenHocPhan());
            ps.setString(2, hp.getMoTa());
            ps.setInt(3, hp.getSoTinChi());
            ps.setDate(4, new Date(hp.getNgayBatDau().getTime()));
            ps.setDate(5, new Date(hp.getNgayKetThuc().getTime()));
            ps.setBoolean(6, hp.isStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String mhp) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "DELETE FROM hocphan WHERE ma_hoc_phan = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, mhp);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
