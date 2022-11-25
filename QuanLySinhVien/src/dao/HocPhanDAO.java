/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.HocPhan;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public interface HocPhanDAO {
    public List<HocPhan> getList();
    public int createOrUpdate (HocPhan hp);
    public HocPhan findByMhp (String mhp);
    public boolean update (HocPhan hp);
    public boolean delete (String mhp);
}
