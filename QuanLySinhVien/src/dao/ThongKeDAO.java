/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import bean.HocPhanBean;
import bean.LopHocBean;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ThongKeDAO {
    public List <LopHocBean> getListByLopHoc();
    public List <HocPhanBean> getListByHocPhan();
}
