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
public class HocPhan {
    private String maHocPhan;
    private String tenHocPhan;
    private String moTa;
    private int soTinChi;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private boolean status;

    public HocPhan() {
    }

    public HocPhan(String maHocPhan, String tenHocPhan, String moTa, int soTinChi, Date ngayBatDau, Date ngayKetThuc, boolean status) {
        this.maHocPhan = maHocPhan;
        this.tenHocPhan = tenHocPhan;
        this.moTa = moTa;
        this.soTinChi = soTinChi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.status = status;
    }

    public String getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public String getTenHocPhan() {
        return tenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        this.tenHocPhan = tenHocPhan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.maHocPhan + " " + this.tenHocPhan + " " + this.moTa + " " + this.soTinChi + " " +
                this.ngayBatDau + " " + this.ngayKetThuc + " " + this.status;
    }

    
    
}
