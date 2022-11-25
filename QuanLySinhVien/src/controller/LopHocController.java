/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.HocPhan;
import model.LopHoc;
import service.HocPhanService;
import service.LopHocService;
import service.LopHocServiceImpl;
import view.HocPhanJFrame;
import view.LopHocJFrame;

/**
 *
 * @author ASUS
 */
public class LopHocController {

    private JButton jbtnSubmit;
    private JTextField jtfMaLopHoc;
    private JTextField jtfMaHocPhan;
    private JTextField jtfMaSinhVien;
    private JDateChooser jdcNgayDangKy;
    private JCheckBox jcbTinhTrang;
    private JLabel jlbMsg;

    private LopHoc lh;
    private LopHocService lopHocService = null;

    public LopHocController(JButton jbtnSubmit, JTextField jtfMaLopHoc, JTextField jtfMaHocPhan, JTextField jtfMaSinhVien, JDateChooser jdcNgayDangKy, JCheckBox jcbTinhTrang, JLabel jlbMsg) {
        this.jbtnSubmit = jbtnSubmit;
        this.jtfMaLopHoc = jtfMaLopHoc;
        this.jtfMaHocPhan = jtfMaHocPhan;
        this.jtfMaSinhVien = jtfMaSinhVien;
        this.jdcNgayDangKy = jdcNgayDangKy;
        this.jcbTinhTrang = jcbTinhTrang;
        this.jlbMsg = jlbMsg;

        this.lopHocService = new LopHocServiceImpl();
    }

    public void setView(LopHoc lh) {
        this.lh = lh;
        jtfMaLopHoc.setText(lh.getMaLopHoc());
        jtfMaHocPhan.setText(lh.getMaHocPhan());
        jtfMaSinhVien.setText(lh.getMaSinhVien());
        jdcNgayDangKy.setDate(lh.getNgayDangKy());
        jcbTinhTrang.setSelected(lh.isStatus());
    }

    public void setEvent() {
        jbtnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String mlh = jtfMaLopHoc.getText();
                LopHocJFrame f = new LopHocJFrame(lh);
                if (jtfMaLopHoc.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {                 
                    lh.setMaLopHoc(jtfMaLopHoc.getText()); 
                    lh.setNgayDangKy(jdcNgayDangKy.getDate());
                    lh.setMaHocPhan(jtfMaHocPhan.getText());
                    lh.setMaSinhVien(jtfMaSinhVien.getText());
                    lh.setStatus(jcbTinhTrang.isSelected());
                    lopHocService.createOrUpdate(lh);
                    JOptionPane.showConfirmDialog(f, "Lớp học đã được thêm vào cơ sở dữ liệu thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaLopHoc.setText("");
                    jtfMaLopHoc.setText("");
                    jtfMaSinhVien.setText("");
                    jdcNgayDangKy.setDateFormatString("");
                    jcbTinhTrang.setSelected(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtnSubmit.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbtnSubmit.setBackground(new Color(112, 219, 112));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                jbtnSubmit.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jbtnSubmit.setBackground(new Color(102, 153, 255));
            }

        });
    }
}
