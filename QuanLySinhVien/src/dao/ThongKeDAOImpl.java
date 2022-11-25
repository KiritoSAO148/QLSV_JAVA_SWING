/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.HocPhanBean;
import bean.LopHocBean;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ThongKeDAOImpl implements ThongKeDAO {

    @Override
    public List<LopHocBean> getListByLopHoc() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT ngay_dang_ky, COUNT(*) AS so_luong FROM lophoc GROUP BY ngay_dang_ky";
            List<LopHocBean> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHocBean lopHocBean = new LopHocBean();
                lopHocBean.setNgayDangKy(rs.getString("ngay_dang_ky"));
                lopHocBean.setSoLuongSinhVien(rs.getInt("so_luong"));
                list.add(lopHocBean);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HocPhanBean> getListByHocPhan() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT ten_hoc_phan, ngay_bat_dau, ngay_ket_thuc FROM hocphan WHERE tinh_trang = TRUE";
            List<HocPhanBean> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocPhanBean hocPhanBean = new HocPhanBean();
                hocPhanBean.setTenHocPhan(rs.getString("ten_hoc_phan"));
                hocPhanBean.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                hocPhanBean.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                list.add(hocPhanBean);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
