/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.LopHoc;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public interface LopHocService {
    public List<LopHoc> getList();
    public int createOrUpdate (LopHoc lh);
    public LopHoc findByMlh (String mlh);
    public boolean update (LopHoc lh);
    public boolean delete (String mlh);
}
