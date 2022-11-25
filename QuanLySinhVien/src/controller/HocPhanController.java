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
public class HocPhanController {

    private JButton jbtnSubmit;
    private JTextField jtfMaHocPhan;
    private JTextField jtfTenHocPhan;
    private JTextField jtfMoTa;
    private JTextField jtfSoTinChi;
    private JDateChooser jdcNgayBatDau;
    private JDateChooser jdcNgayKetThuc;
    private JCheckBox jcbTinhTrang;
    private JLabel jlbMsg;

    private HocPhan hp;
    private HocPhanService hocPhanService = null;

    public HocPhanController(JButton jbtnSubmit, JTextField jtfMaHocPhan, JTextField jtfTenHocPhan, JTextField jtfMoTa, JTextField jtfSoTinChi, JDateChooser jdcNgayBatDau, JDateChooser jdcNgayKetThuc, JCheckBox jcbTinhTrang, JLabel jlbMsg) {
        this.jbtnSubmit = jbtnSubmit;
        this.jtfMaHocPhan = jtfMaHocPhan;
        this.jtfTenHocPhan = jtfTenHocPhan;
        this.jtfMoTa = jtfMoTa;
        this.jtfSoTinChi = jtfSoTinChi;
        this.jdcNgayBatDau = jdcNgayBatDau;
        this.jdcNgayKetThuc = jdcNgayKetThuc;
        this.jcbTinhTrang = jcbTinhTrang;
        this.jlbMsg = jlbMsg;

        this.hocPhanService = new HocPhanServiceImpl();
    }

    public void setView(HocPhan hp) {
        this.hp = hp;
        jtfMaHocPhan.setText(hp.getMaHocPhan());
        jtfTenHocPhan.setText(hp.getTenHocPhan());
        jtfMoTa.setText(hp.getMoTa());
        jtfSoTinChi.setText(hp.getSoTinChi() + "");
        jdcNgayBatDau.setDate(hp.getNgayBatDau());
        jdcNgayKetThuc.setDate(hp.getNgayKetThuc());
        jcbTinhTrang.setSelected(hp.isStatus());
    }
    
    public void setEvent() {
        jbtnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String mhp = jtfMaHocPhan.getText();
                HocPhanJFrame f = new HocPhanJFrame(hp);
                if (jtfMaHocPhan.getText().length() == 0 || jtfTenHocPhan.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    hp.setMaHocPhan(jtfMaHocPhan.getText());
                    hp.setNgayBatDau(jdcNgayBatDau.getDate());
                    hp.setNgayKetThuc(jdcNgayKetThuc.getDate());
                    hp.setTenHocPhan(jtfTenHocPhan.getText());
                    hp.setMoTa(jtfMoTa.getText());
                    hp.setSoTinChi(Integer.parseInt(jtfSoTinChi.getText()));
                    hp.setStatus(jcbTinhTrang.isSelected());
                    hocPhanService.createOrUpdate(hp);
                    JOptionPane.showConfirmDialog(f, "Học phần đã được thêm vào cơ sở dữ liệu thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaHocPhan.setText("");
                    jtfTenHocPhan.setText("");
                    jtfMoTa.setText("");
                    jtfSoTinChi.setText("");
                    jdcNgayBatDau.setDateFormatString("");
                    jdcNgayKetThuc.setDateFormatString("");
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
