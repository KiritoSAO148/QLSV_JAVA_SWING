/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BangDiemDAO;
import dao.BangDiemDAOImpl;
import java.util.List;
import model.BangDiem;
import model.HocPhan;

/**
 *
 * @author ASUS
 */
public class BangDiemServiceImpl implements BangDiemService{

    private BangDiemDAO bangDiemDAO = null;

    public BangDiemServiceImpl() {
        bangDiemDAO = new BangDiemDAOImpl();
    }
    
    
    
    @Override
    public List<BangDiem> getList() {
        return bangDiemDAO.getList();
    }

    @Override
    public int createOrUpdate(BangDiem bd) {
        return bangDiemDAO.createOrUpdate(bd);
    }

    @Override
    public BangDiem findByMsv(String msv) {
        return bangDiemDAO.findByMsv(msv);
    }

    @Override
    public boolean update(BangDiem bd) {
        return bangDiemDAO.update(bd);
    }

    @Override
    public boolean delete(String msv) {
        return bangDiemDAO.delete(msv);
    }

    
    
}
