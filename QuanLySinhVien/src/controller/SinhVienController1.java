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

/**
 *
 * @author ASUS
 */
public class SinhVienController1 {

    private JButton jbtnUpdate;
    private JButton jbtnSearch;
    private JButton jbtnDelete;
    private JTextField jtfMaSinhVien1;
    private JTextField jtfHoTen1;
    private JDateChooser jdcNgaySinh1;
    private JRadioButton jrdNam1;
    private JRadioButton jrdNu1;
    private JTextField jtfSoDienThoai1;
    private JTextArea jtaDiaChi1;
    private JTextField jtfNganh1;
    private JTextField jtfGpa1;
    private JCheckBox jcbTinhTrang1;
    private JLabel jlbMsg1;

    private SinhVien sv = null;
    private SinhVienService sinhVienService = null;

    public SinhVienController1(JButton jbtnUpdate, JButton jbtnSearch, JButton jbtnDelete, JTextField jtfMaSinhVien1, JTextField jtfHoTen1, JDateChooser jdcNgaySinh1, JRadioButton jrdNam1, JRadioButton jrdNu1, JTextField jtfSoDienThoai1, JTextArea jtaDiaChi1, JTextField jtfNganh1, JTextField jtfGpa1, JCheckBox jcbTinhTrang1, JLabel jlbMsg1) {
        this.jbtnUpdate = jbtnUpdate;
        this.jbtnSearch = jbtnSearch;
        this.jbtnDelete = jbtnDelete;
        this.jtfMaSinhVien1 = jtfMaSinhVien1;
        this.jtfHoTen1 = jtfHoTen1;
        this.jdcNgaySinh1 = jdcNgaySinh1;
        this.jrdNam1 = jrdNam1;
        this.jrdNu1 = jrdNu1;
        this.jtfSoDienThoai1 = jtfSoDienThoai1;
        this.jtaDiaChi1 = jtaDiaChi1;
        this.jtfNganh1 = jtfNganh1;
        this.jtfGpa1 = jtfGpa1;
        this.jcbTinhTrang1 = jcbTinhTrang1;
        this.jlbMsg1 = jlbMsg1;
        
        this.sinhVienService = new SinhVienServiceImpl();
    }

    


    public void setView(SinhVien sv) {
        this.sv = sv;
        jtfMaSinhVien1.setText(sv.getMaSinhVien());
        jtfHoTen1.setText(sv.getHoTen());
        jdcNgaySinh1.setDate(sv.getNgaySinh());
        if (sv.getGioiTinh()) {
            jrdNam1.setSelected(true);
            jrdNu1.setSelected(false);
        } else {
            jrdNam1.setSelected(false);
            jrdNu1.setSelected(true);
        }
        jtfSoDienThoai1.setText(sv.getSoDienThoai());
        jtaDiaChi1.setText(sv.getDiaChi());
        jtfNganh1.setText(sv.getNganh());
        jtfGpa1.setText(sv.getGpa());
        jcbTinhTrang1.setSelected(sv.getStatus());
    }

    public void setEvent() {
        jbtnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SinhVienJFrame f = new SinhVienJFrame(sv);
                if (jtfMaSinhVien1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    SinhVien sv = sinhVienService.findByMsv(jtfMaSinhVien1.getText());
                    if (sv != null) {
                        jtfHoTen1.setText(sv.getHoTen());
                        jdcNgaySinh1.setDate(sv.getNgaySinh());
                        jrdNam1.setSelected(sv.getGioiTinh());
                        jrdNu1.setSelected(sv.getGioiTinh());
                        jtfSoDienThoai1.setText(sv.getSoDienThoai());
                        jtaDiaChi1.setText(sv.getDiaChi());
                        jtfNganh1.setText(sv.getNganh());
                        jtfGpa1.setText(sv.getGpa());
                        jcbTinhTrang1.setSelected(sv.getStatus());
                        JOptionPane.showConfirmDialog(f, "Tìm thấy sinh viên trong CSDL!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        
                    } else {
                        jtfHoTen1.setText("");
                        jdcNgaySinh1.setDateFormatString("");
                        jrdNam1.setSelected(false);
                        jrdNu1.setSelected(false);
                        jtfSoDienThoai1.setText("");
                        jtaDiaChi1.setText("");
                        jtfNganh1.setText("");
                        jtfGpa1.setText("");
                        jcbTinhTrang1.setSelected(false);
                        JOptionPane.showConfirmDialog(f, "Không tìm thấy sinh viên trong CSDL!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtnSearch.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbtnSearch.setBackground(new Color(112, 219, 112));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                jbtnSearch.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jbtnSearch.setBackground(new Color(102, 153, 255));
            }

        });

        jbtnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SinhVienJFrame f = new SinhVienJFrame(sv);
                if (jtfMaSinhVien1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    SinhVien sv = new SinhVien();
                    sv.setMaSinhVien(jtfMaSinhVien1.getText());
                    sv.setNgaySinh(jdcNgaySinh1.getDate());
                    sv.setHoTen(jtfHoTen1.getText());
                    sv.setGioiTinh(jrdNam1.isSelected());
                    sv.setSoDienThoai(jtfSoDienThoai1.getText());
                    sv.setDiaChi(jtaDiaChi1.getText());
                    sv.setNganh(jtfNganh1.getText());
                    sv.setGpa(jtfGpa1.getText());
                    sv.setStatus(jcbTinhTrang1.isSelected());
                    sinhVienService.update(sv);
                    JOptionPane.showConfirmDialog(f, "Cập nhật dữ liệu thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaSinhVien1.setText("");
                    jtfHoTen1.setText("");
                    jdcNgaySinh1.setDateFormatString("");
                    jrdNam1.setSelected(true);
                    jrdNu1.setSelected(false);
                    jtfSoDienThoai1.setText("");
                    jtaDiaChi1.setText("");
                    jtfNganh1.setText("");
                    jtfGpa1.setText("");
                    jcbTinhTrang1.setSelected(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtnUpdate.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbtnUpdate.setBackground(new Color(112, 219, 112));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                jbtnUpdate.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jbtnUpdate.setBackground(new Color(102, 153, 255));
            }

        });
        
        jbtnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SinhVienJFrame f = new SinhVienJFrame(sv);
                if (jtfMaSinhVien1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    sinhVienService.delete(jtfMaSinhVien1.getText());
                    JOptionPane.showConfirmDialog(f, "Sinh viên được xóa khỏi CSDL thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaSinhVien1.setText("");
                    jtfHoTen1.setText("");
                    jdcNgaySinh1.setDateFormatString("");
                    jrdNam1.setSelected(true);
                    jrdNu1.setSelected(false);
                    jtfSoDienThoai1.setText("");
                    jtaDiaChi1.setText("");
                    jtfNganh1.setText("");
                    jtfGpa1.setText("");
                    jcbTinhTrang1.setSelected(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtnDelete.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbtnDelete.setBackground(new Color(112, 219, 112));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                jbtnDelete.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jbtnDelete.setBackground(new Color(102, 153, 255));
            }

        });
    }
}
