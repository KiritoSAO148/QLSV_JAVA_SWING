/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public interface SinhVienService {
    public List <SinhVien> getList();
    public int createOrUpdate (SinhVien sv);
    public SinhVien findByMsv (String msv);
    public boolean update (SinhVien sv);
    public boolean delete (String msv);
}
