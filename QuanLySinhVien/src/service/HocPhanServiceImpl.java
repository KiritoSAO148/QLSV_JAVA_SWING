/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.HocPhanDAO;
import dao.HocPhanDAOImpl;
import java.util.List;
import model.HocPhan;

/**
 *
 * @author ASUS
 */
public class HocPhanServiceImpl implements HocPhanService{
    
    private HocPhanDAO hocPhanDAO = null;

    public HocPhanServiceImpl() {
        hocPhanDAO = new HocPhanDAOImpl();
    }
    

    @Override
    public List<HocPhan> getList() {
        return hocPhanDAO.getList();
    }

    @Override
    public int createOrUpdate(HocPhan hp) {
        return hocPhanDAO.createOrUpdate(hp);
    }

    @Override
    public HocPhan findByMhp(String mhp) {
        return hocPhanDAO.findByMhp(mhp);
    }

    @Override
    public boolean update(HocPhan hp) {
        return hocPhanDAO.update(hp);
    }

    @Override
    public boolean delete(String mhp) {
        return hocPhanDAO.delete(mhp);
    }
    
}
