/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.BangDiem;
import model.HocPhan;

/**
 *
 * @author ASUS
 */
public interface BangDiemDAO {
    public List<BangDiem> getList();
    public int createOrUpdate (BangDiem bd);
    public BangDiem findByMsv (String msv);
    public boolean update (BangDiem bd);
    public boolean delete (String msv);
}
