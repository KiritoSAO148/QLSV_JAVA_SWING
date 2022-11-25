/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class LopHoc {
    private String maLopHoc;
    private String maHocPhan;
    private String maSinhVien;
    private Date ngayDangKy;
    private boolean status;

    public LopHoc() {
    }

    public LopHoc(String maLopHoc, String maHocPhan, String maSinhVien, Date ngayDangKy, boolean status) {
        this.maLopHoc = maLopHoc;
        this.maHocPhan = maHocPhan;
        this.maSinhVien = maSinhVien;
        this.ngayDangKy = ngayDangKy;
        this.status = status;
    }

    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public String getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
}
