/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.HocPhan;
import model.LopHoc;
import model.SinhVien;
import service.HocPhanService;
import service.HocPhanServiceImpl;
import service.LopHocService;
import service.LopHocServiceImpl;
import view.HocPhanJFrame;
import view.LopHocJFrame;
import view.SinhVienJFrame;

/**
 *
 * @author ASUS
 */
public class LopHocController1 {

    private JButton jbtnUpdate;
    private JButton jbtnSearch;
    private JButton jbtnDelete;
    private JTextField jtfMaLopHoc1;
    private JTextField jtfMaHocPhan1;
    private JTextField jtfMaSinhVien1;
    private JDateChooser jdcNgayDangKy1;
    private JCheckBox jcbTinhTrang1;
    private JLabel jlbMsg1;

    private LopHoc lh;
    private LopHocService lopHocService = null;

    public LopHocController1(JButton jbtnUpdate, JButton jbtnSearch, JButton jbtnDelete, JTextField jtfMaLopHoc1, JTextField jtfMaHocPhan1, JTextField jtfMaSinhVien1, JDateChooser jdcNgayDangKy1, JCheckBox jcbTinhTrang1, JLabel jlbMsg1) {
        this.jbtnUpdate = jbtnUpdate;
        this.jbtnSearch = jbtnSearch;
        this.jbtnDelete = jbtnDelete;
        this.jtfMaLopHoc1 = jtfMaLopHoc1;
        this.jtfMaHocPhan1 = jtfMaHocPhan1;
        this.jtfMaSinhVien1 = jtfMaSinhVien1;
        this.jdcNgayDangKy1 = jdcNgayDangKy1;
        this.jcbTinhTrang1 = jcbTinhTrang1;
        this.jlbMsg1 = jlbMsg1;

        this.lopHocService = new LopHocServiceImpl();
    }

    public void setView(LopHoc lh) {
        this.lh = lh;
        jtfMaLopHoc1.setText(lh.getMaLopHoc());
        jtfMaHocPhan1.setText(lh.getMaHocPhan());
        jtfMaSinhVien1.setText(lh.getMaSinhVien());
        jdcNgayDangKy1.setDate(lh.getNgayDangKy());
        jcbTinhTrang1.setSelected(lh.isStatus());
    }

    public void setEvent() {
        jbtnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LopHocJFrame f = new LopHocJFrame(lh);
                if (jtfMaLopHoc1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    LopHoc lh = lopHocService.findByMlh(jtfMaLopHoc1.getText());
                    if (lh != null) {
                        jtfMaLopHoc1.setText(lh.getMaLopHoc());
                        jtfMaHocPhan1.setText(lh.getMaHocPhan());
                        jtfMaSinhVien1.setText(lh.getMaSinhVien());
                        jdcNgayDangKy1.setDate(lh.getNgayDangKy());
                        jcbTinhTrang1.setSelected(lh.isStatus());
                        JOptionPane.showConfirmDialog(f, "Tìm thấy lớp học trong CSDL!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        jtfMaLopHoc1.setText("");
                        jtfMaHocPhan1.setText("");
                        jtfMaSinhVien1.setText("");
                        jdcNgayDangKy1.setDateFormatString("");
                        jcbTinhTrang1.setSelected(false);
                        JOptionPane.showConfirmDialog(f, "Không tìm thấy lớp học trong CSDL!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
                LopHocJFrame f = new LopHocJFrame(lh);
                if (jtfMaLopHoc1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    LopHoc lh = new LopHoc();
                    lh.setMaLopHoc(jtfMaLopHoc1.getText());
                    lh.setMaHocPhan(jtfMaHocPhan1.getText());
                    lh.setMaSinhVien(jtfMaSinhVien1.getText());
                    lh.setNgayDangKy(jdcNgayDangKy1.getDate());
                    lh.setStatus(jcbTinhTrang1.isSelected());
                    lopHocService.update(lh);
                    JOptionPane.showConfirmDialog(f, "Cập nhật dữ liệu thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaLopHoc1.setText("");
                    jtfMaHocPhan1.setText("");
                    jtfMaSinhVien1.setText("");
                    jdcNgayDangKy1.setDateFormatString("");
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
                LopHocJFrame f = new LopHocJFrame(lh);
                if (jtfMaLopHoc1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    lopHocService.delete(jtfMaLopHoc1.getText());
                    JOptionPane.showConfirmDialog(f, "Lớp học được xóa khỏi CSDL thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaLopHoc1.setText("");
                    jtfMaHocPhan1.setText("");
                    jtfMaSinhVien1.setText("");
                    jdcNgayDangKy1.setDateFormatString("");
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
