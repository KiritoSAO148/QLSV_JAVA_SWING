/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class BangDiem {
    private String maSinhVien;
    private String tenSinhVien;
    private String maHocPhan;
    private String tenHocPhan;
    private int soTinChi;
    private double diemcc;
    private double diemkt;
    private double diemth;
    private double diembt;
    private double end;

    public BangDiem() {
    }

    public BangDiem(String maSinhVien, String tenSinhVien, String maHocPhan, String tenHocPhan, int soTinChi, double diemcc, double diemkt, double diemth, double diembt, double end) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.maHocPhan = maHocPhan;
        this.tenHocPhan = tenHocPhan;
        this.soTinChi = soTinChi;
        this.diemcc = diemcc;
        this.diemkt = diemkt;
        this.diemth = diemth;
        this.diembt = diembt;
        this.end = end;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
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

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public double getDiemcc() {
        return diemcc;
    }

    public void setDiemcc(double diemcc) {
        this.diemcc = diemcc;
    }

    public double getDiemkt() {
        return diemkt;
    }

    public void setDiemkt(double diemkt) {
        this.diemkt = diemkt;
    }

    public double getDiemth() {
        return diemth;
    }

    public void setDiemth(double diemth) {
        this.diemth = diemth;
    }

    public double getDiembt() {
        return diembt;
    }

    public void setDiembt(double diembt) {
        this.diembt = diembt;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }
    
    public boolean check(){
        if (this.diembt == 0 || this.diemcc == 0 || this.diemkt == 0 || this.diemth == 0)
            return false;
        return true;
    }
    
    public double getTK(){
        return this.diemcc * 0.1 + this.diemkt * 0.1 + this.diemth * 0.1 + this.diembt * 0.1 + this.end * 0.6;
    }
    
    public String getXL(){
        if (this.check() && this.getTK() >= 9.0) return "A+";
        if (this.check() && this.getTK() >= 8.5) return "A";
        if (this.check() && this.getTK() >= 8.0) return "B+";
        if (this.check() && this.getTK() >= 7.0) return "B";
        if (this.check() && this.getTK() >= 6.5) return "C+";
        if (this.check() && this.getTK() >= 5.0) return "C";
        if (this.check() && this.getTK() >= 4.0) return "D";
        return "F";
    }
    
    public String getKQ(){
        if (this.check() && this.getTK() >= 4.0) return "Đạt";
        return "X";
    }

    @Override
    public String toString() {
        return "BangDiem{" + '}';
    }
    
    
}
