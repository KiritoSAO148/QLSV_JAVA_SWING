/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import bean.HocPhanBean;
import bean.LopHocBean;
import dao.ThongKeDAO;
import dao.ThongKeDAOImpl;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ThongKeServiceImpl implements ThongKeService{

    private ThongKeDAO thongKeDAO = null;

    public ThongKeServiceImpl() {
        thongKeDAO = new ThongKeDAOImpl();
    }
    
    
    
    @Override
    public List<LopHocBean> getListByLopHoc() {
        return thongKeDAO.getListByLopHoc();
    }

    @Override
    public List<HocPhanBean> getListByHocPhan() {
        return thongKeDAO.getListByHocPhan();
    }
    
}
