/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.SinhVienDAO;
import dao.SinhVienDAOImpl;
import java.util.List;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public class SinhVienServiceImpl implements SinhVienService{

    private SinhVienDAO sinhVienDAO = null;

    public SinhVienServiceImpl() {
        sinhVienDAO = new SinhVienDAOImpl();
    }
    
    
    
    @Override
    public List<SinhVien> getList() {
        return sinhVienDAO.getList();
    }

    @Override
    public int createOrUpdate(SinhVien sv) {
        return sinhVienDAO.createOrUpdate(sv);
    }

    @Override
    public SinhVien findByMsv(String msv) {
        return sinhVienDAO.findByMsv(msv);
    }

    @Override
    public boolean update(SinhVien sv) {
        return sinhVienDAO.update(sv);
    }

    @Override
    public boolean delete(String msv) {
        return sinhVienDAO.delete(msv);
    }
    
    
}
