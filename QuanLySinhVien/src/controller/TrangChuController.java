/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.JLabel;
import model.HocPhan;
import model.LopHoc;
import model.SinhVien;
import service.HocPhanService;
import service.HocPhanServiceImpl;
import service.LopHocService;
import service.LopHocServiceImpl;
import service.SinhVienService;
import service.SinhVienServiceImpl;

/**
 *
 * @author ASUS
 */
public class TrangChuController {
    
    private JLabel jlbSinhVien;
    private JLabel jlbHocPhan;
    private JLabel jlbLopHoc;
    
    private SinhVienService sinhVienService = null;
    private HocPhanService hocPhanService = null;
    private LopHocService lopHocService = null;

    public TrangChuController(JLabel jlbSinhVien, JLabel jlbHocPhan, JLabel jlbLopHoc) {
        this.jlbSinhVien = jlbSinhVien;
        this.jlbHocPhan = jlbHocPhan;
        this.jlbLopHoc = jlbLopHoc;
        
        sinhVienService = new SinhVienServiceImpl();
        hocPhanService = new HocPhanServiceImpl();
        lopHocService = new LopHocServiceImpl();
    }

    
    
    public void setEvent(){
        List <SinhVien> listSinhVien = sinhVienService.getList();
        List <HocPhan> listHocPhan = hocPhanService.getList();
        List <LopHoc> listLopHoc = lopHocService.getList();
        
        jlbSinhVien.setText(listSinhVien.size() + "");
        jlbHocPhan.setText(listHocPhan.size() + "");
        jlbLopHoc.setText(listLopHoc.size() + "");
    }
    
}
