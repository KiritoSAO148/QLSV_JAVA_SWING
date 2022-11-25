/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author ASUS
 */
public class LopHocBean {
    private String ngayDangKy;
    private int soLuongSinhVien;

    public LopHocBean() {
    }

    public LopHocBean(String ngayDangKy, int soLuongSinhVien) {
        this.ngayDangKy = ngayDangKy;
        this.soLuongSinhVien = soLuongSinhVien;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public int getSoLuongSinhVien() {
        return soLuongSinhVien;
    }

    public void setSoLuongSinhVien(int soLuongSinhVien) {
        this.soLuongSinhVien = soLuongSinhVien;
    }
    
    
}
