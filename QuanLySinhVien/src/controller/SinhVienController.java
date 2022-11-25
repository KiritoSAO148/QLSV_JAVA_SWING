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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.SinhVien;
import service.SinhVienService;
import service.SinhVienServiceImpl;
import view.SinhVienJFrame;
import view.SinhVienJPanel;

/**
 *
 * @author ASUS
 */
public class SinhVienController {
    private JButton jbtnSubmit;
    private JTextField jtfMaSinhVien;
    private JTextField jtfHoTen;
    private JDateChooser jdcNgaySinh;
    private JRadioButton jrdNam;
    private JRadioButton jrdNu;
    private JTextField jtfSoDienThoai;
    private JTextArea jtaDiaChi;
    private JTextField jtfNganh;
    private JTextField jtfGpa;
    private JCheckBox jcbTinhTrang;
    private JLabel jlbMsg;

    private SinhVien sv;
    private SinhVienService sinhVienService = null;

    public SinhVienController(JButton jbtnSubmit, JTextField jtfMaSinhVien, JTextField jtfHoTen, JDateChooser jdcNgaySinh, JRadioButton jrdNam, JRadioButton jrdNu, JTextField jtfSoDienThoai, JTextArea jtaDiaChi, JTextField jtfNganh, JTextField jtfGpa, JCheckBox jcbTinhTrang, JLabel jlbMsg) {
        this.jbtnSubmit = jbtnSubmit;
        this.jtfMaSinhVien = jtfMaSinhVien;
        this.jtfHoTen = jtfHoTen;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jrdNam = jrdNam;
        this.jrdNu = jrdNu;
        this.jtfSoDienThoai = jtfSoDienThoai;
        this.jtaDiaChi = jtaDiaChi;
        this.jtfNganh = jtfNganh;
        this.jtfGpa = jtfGpa;
        this.jcbTinhTrang = jcbTinhTrang;
        this.jlbMsg = jlbMsg;
        
        this.sinhVienService = new SinhVienServiceImpl();
    }

    

    public void setView(SinhVien sv) {
        this.sv = sv;
        jtfMaSinhVien.setText(sv.getMaSinhVien());
        jtfHoTen.setText(sv.getHoTen());
        jdcNgaySinh.setDate(sv.getNgaySinh());
        if (sv.getGioiTinh()) {
            jrdNam.setSelected(true);
            jrdNu.setSelected(false);
        } else {
            jrdNam.setSelected(false);
            jrdNu.setSelected(true);
        }
        jtfSoDienThoai.setText(sv.getSoDienThoai());
        jtaDiaChi.setText(sv.getDiaChi());
        jtfNganh.setText(sv.getNganh());
        jtfGpa.setText(sv.getGpa());
        jcbTinhTrang.setSelected(sv.getStatus());
    }

    public void setEvent() {
        jbtnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String msv = jtfMaSinhVien.getText();
                SinhVienJFrame f = new SinhVienJFrame(sv);
                if (jtfMaSinhVien.getText().length() == 0 || jtfHoTen.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    sv.setMaSinhVien(jtfMaSinhVien.getText());
                    sv.setNgaySinh(jdcNgaySinh.getDate());
                    sv.setHoTen(jtfHoTen.getText());
                    sv.setGioiTinh(jrdNam.isSelected());
                    sv.setSoDienThoai(jtfSoDienThoai.getText());
                    sv.setDiaChi(jtaDiaChi.getText());
                    sv.setNganh(jtfNganh.getText());
                    sv.setGpa(jtfGpa.getText());
                    sv.setStatus(jcbTinhTrang.isSelected());
                    sinhVienService.createOrUpdate(sv);
                    JOptionPane.showConfirmDialog(f, "Sinh viên đã được thêm vào cơ sở dữ liệu thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaSinhVien.setText("");
                    jtfHoTen.setText("");
                    jdcNgaySinh.setDateFormatString("");
                    jrdNam.setSelected(true);
                    jrdNu.setSelected(false);
                    jtfSoDienThoai.setText("");
                    jtaDiaChi.setText("");
                    jtfNganh.setText("");
                    jtfGpa.setText("");
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
