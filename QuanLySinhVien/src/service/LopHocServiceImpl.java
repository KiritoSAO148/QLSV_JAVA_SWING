/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.LopHocDAO;
import dao.LopHocDAOImpl;
import java.util.List;
import model.LopHoc;

/**
 *
 * @author ASUS
 */
public class LopHocServiceImpl implements LopHocService{
    
    private LopHocDAO lopHocDAO = null;

    public LopHocServiceImpl() {
        lopHocDAO = new LopHocDAOImpl();
    }
    
    

    @Override
    public List<LopHoc> getList() {
        return lopHocDAO.getList();
    }

    @Override
    public int createOrUpdate(LopHoc lh) {
        return lopHocDAO.createOrUpdate(lh);
    }

    @Override
    public LopHoc findByMlh(String mlh) {
        return lopHocDAO.findByMlh(mlh);
    }

    @Override
    public boolean update(LopHoc lh) {
        return lopHocDAO.update(lh);
    }

    @Override
    public boolean delete(String mlh) {
        return lopHocDAO.delete(mlh);
    }
    
}
