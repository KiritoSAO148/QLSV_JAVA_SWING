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
import model.SinhVien;
import service.HocPhanService;
import service.HocPhanServiceImpl;
import view.HocPhanJFrame;
import view.SinhVienJFrame;

/**
 *
 * @author ASUS
 */
public class HocPhanController1 {

    private JButton jbtnUpdate;
    private JButton jbtnSearch;
    private JButton jbtnDelete;
    private JTextField jtfMaHocPhan1;
    private JTextField jtfTenHocPhan1;
    private JTextField jtfMoTa1;
    private JTextField jtfSoTinChi1;
    private JDateChooser jdcNgayBatDau1;
    private JDateChooser jdcNgayKetThuc1;
    private JCheckBox jcbTinhTrang1;
    private JLabel jlbMsg1;

    private HocPhan hp;
    private HocPhanService hocPhanService = null;

    public HocPhanController1(JButton jbtnUpdate, JButton jbtnSearch, JButton jbtnDelete, JTextField jtfMaHocPhan1, JTextField jtfTenHocPhan1, JTextField jtfMoTa1, JTextField jtfSoTinChi1, JDateChooser jdcNgayBatDau1, JDateChooser jdcNgayKetThuc1, JCheckBox jcbTinhTrang1, JLabel jlbMsg1) {
        this.jbtnUpdate = jbtnUpdate;
        this.jbtnSearch = jbtnSearch;
        this.jbtnDelete = jbtnDelete;
        this.jtfMaHocPhan1 = jtfMaHocPhan1;
        this.jtfTenHocPhan1 = jtfTenHocPhan1;
        this.jtfMoTa1 = jtfMoTa1;
        this.jtfSoTinChi1 = jtfSoTinChi1;
        this.jdcNgayBatDau1 = jdcNgayBatDau1;
        this.jdcNgayKetThuc1 = jdcNgayKetThuc1;
        this.jcbTinhTrang1 = jcbTinhTrang1;
        this.jlbMsg1 = jlbMsg1;
        
        this.hocPhanService = new HocPhanServiceImpl();
    }

    

    

    public void setView(HocPhan hp) {
        this.hp = hp;
        jtfMaHocPhan1.setText(hp.getMaHocPhan());
        jtfTenHocPhan1.setText(hp.getTenHocPhan());
        jtfMoTa1.setText(hp.getMoTa());
        jtfSoTinChi1.setText(hp.getSoTinChi() + "");
        jdcNgayBatDau1.setDate(hp.getNgayBatDau());
        jdcNgayKetThuc1.setDate(hp.getNgayKetThuc());
        jcbTinhTrang1.setSelected(hp.isStatus());
    }

    public void setEvent() {
        jbtnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HocPhanJFrame f = new HocPhanJFrame(hp);
                if (jtfMaHocPhan1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    HocPhan hp = hocPhanService.findByMhp(jtfMaHocPhan1.getText());
                    if (hp != null) {
                        jtfMaHocPhan1.setText(hp.getMaHocPhan());
                        jtfTenHocPhan1.setText(hp.getTenHocPhan());
                        jtfMoTa1.setText(hp.getMoTa());
                        jtfSoTinChi1.setText(hp.getSoTinChi() + "");
                        jdcNgayBatDau1.setDate(hp.getNgayBatDau());
                        jdcNgayKetThuc1.setDate(hp.getNgayKetThuc());
                        jcbTinhTrang1.setSelected(hp.isStatus());
                        JOptionPane.showConfirmDialog(f, "Tìm thấy học phần trong CSDL!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        
                    } else {
                        jtfTenHocPhan1.setText("");
                        jtfMoTa1.setText("");
                        jtfSoTinChi1.setText("");
                        jdcNgayBatDau1.setDateFormatString("");
                        jdcNgayKetThuc1.setDateFormatString("");
                        jcbTinhTrang1.setSelected(false);
                        JOptionPane.showConfirmDialog(f, "Không tìm thấy học phần trong CSDL!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
                HocPhanJFrame f = new HocPhanJFrame(hp);
                if (jtfMaHocPhan1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    HocPhan hp = new HocPhan();
                    hp.setMaHocPhan(jtfMaHocPhan1.getText());
                    hp.setTenHocPhan(jtfTenHocPhan1.getText());
                    hp.setMoTa(jtfMoTa1.getText());
                    hp.setSoTinChi(Integer.parseInt(jtfSoTinChi1.getText()));
                    hp.setNgayBatDau(jdcNgayBatDau1.getDate());
                    hp.setNgayKetThuc(jdcNgayKetThuc1.getDate());
                    hp.setStatus(jcbTinhTrang1.isSelected());
                    hocPhanService.update(hp);
                    JOptionPane.showConfirmDialog(f, "Cập nhật dữ liệu thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaHocPhan1.setText("");
                    jtfTenHocPhan1.setText("");
                    jtfMoTa1.setText("");
                    jtfSoTinChi1.setText("");
                    jdcNgayBatDau1.setDateFormatString("");
                    jdcNgayKetThuc1.setDateFormatString("");
                        
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
                HocPhanJFrame f = new HocPhanJFrame(hp);
                if (jtfMaHocPhan1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    hocPhanService.delete(jtfMaHocPhan1.getText());
                    JOptionPane.showConfirmDialog(f, "Học phần được xóa khỏi CSDL thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    
                    jtfMaHocPhan1.setText("");
                    jtfTenHocPhan1.setText("");
                    jtfMoTa1.setText("");
                    jtfSoTinChi1.setText("");
                    jdcNgayBatDau1.setDateFormatString("");
                    jdcNgayKetThuc1.setDateFormatString("");
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
