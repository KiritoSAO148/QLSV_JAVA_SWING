/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.HocPhan;

/**
 *
 * @author ASUS
 */
public interface HocPhanService {
    public List<HocPhan> getList();
    public int createOrUpdate (HocPhan hp);
    public HocPhan findByMhp (String mhp);
    public boolean update (HocPhan hp);
    public boolean delete (String mhp);
}
