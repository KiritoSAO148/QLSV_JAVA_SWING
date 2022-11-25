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
import model.BangDiem;
import model.SinhVien;
import service.BangDiemService;
import service.BangDiemServiceImpl;
import service.SinhVienService;
import service.SinhVienServiceImpl;
import view.BangDiemJFrame;
import view.SinhVienJFrame;

/**
 *
 * @author ASUS
 */
public class BangDiemController {
    
    private JTextField jtfMaSinhVien;
    private JTextField jtfHoTen;
    private JTextField jtfMaHocPhan;
    private JTextField jtfTenHocPhan;
    private JTextField jtfSoTinChi;
    private JTextField jtfCC;
    private JTextField jtfKT;
    private JTextField jtfTH;
    private JTextField jtfBT;
    private JTextField jtfThi;
    private JLabel jlbMsg;
    private JButton jbtnSubmit;
    

    private BangDiem bd;
    private BangDiemService bangDiemService = null;

    public BangDiemController(JTextField jtfMaSinhVien, JTextField jtfHoTen, JTextField jtfMaHocPhan, JTextField jtfTenHocPhan, JTextField jtfSoTinChi, JTextField jtfCC, JTextField jtfKT, JTextField jtfTH, JTextField jtfBT, JTextField jtfThi, JLabel jlbMsg, JButton jbtnSubmit) {
        this.jtfMaSinhVien = jtfMaSinhVien;
        this.jtfHoTen = jtfHoTen;
        this.jtfMaHocPhan = jtfMaHocPhan;
        this.jtfTenHocPhan = jtfTenHocPhan;
        this.jtfSoTinChi = jtfSoTinChi;
        this.jtfCC = jtfCC;
        this.jtfKT = jtfKT;
        this.jtfTH = jtfTH;
        this.jtfBT = jtfBT;
        this.jtfThi = jtfThi;
        this.jlbMsg = jlbMsg;
        this.jbtnSubmit = jbtnSubmit;
        
        this.bangDiemService = new BangDiemServiceImpl();
    }

    

    

    public void setView(BangDiem bd) {
        this.bd = bd;
        jtfMaSinhVien.setText(bd.getMaSinhVien());
        jtfHoTen.setText(bd.getTenSinhVien());
        jtfMaHocPhan.setText(bd.getMaHocPhan());
        jtfTenHocPhan.setText(bd.getTenHocPhan());
        jtfSoTinChi.setText(bd.getSoTinChi() + "");
        jtfCC.setText(bd.getDiemcc() + "");
        jtfKT.setText(bd.getDiemkt() + "");
        jtfTH.setText(bd.getDiemth() + "");
        jtfBT.setText(bd.getDiembt() + "");
        jtfThi.setText(bd.getEnd() + "");
    }

    public void setEvent() {
        jbtnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String msv = jtfMaSinhVien.getText();
                BangDiemJFrame f = new BangDiemJFrame(bd);
                if (jtfMaSinhVien.getText().length() == 0 || jtfHoTen.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    bd.setMaSinhVien(jtfMaSinhVien.getText());
                    bd.setTenSinhVien(jtfHoTen.getText());
                    bd.setMaHocPhan(jtfMaHocPhan.getText());
                    bd.setTenHocPhan(jtfTenHocPhan.getText());
                    bd.setSoTinChi(Integer.parseInt(jtfSoTinChi.getText()));
                    bd.setDiemcc(Double.parseDouble(jtfCC.getText()));
                    bd.setDiemkt(Double.parseDouble(jtfKT.getText()));
                    bd.setDiemth(Double.parseDouble(jtfTH.getText()));
                    bd.setDiembt(Double.parseDouble(jtfBT.getText()));
                    bd.setEnd(Double.parseDouble(jtfThi.getText()));
                    bangDiemService.createOrUpdate(bd);
                    JOptionPane.showConfirmDialog(f, "Bảng điểm đã được thêm vào cơ sở dữ liệu thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaSinhVien.setText("");
                    jtfHoTen.setText("");
                    jtfMaHocPhan.setText("");
                    jtfTenHocPhan.setText("");
                    jtfSoTinChi.setText("");
                    jtfCC.setText("");
                    jtfKT.setText("");
                    jtfTH.setText("");
                    jtfBT.setText("");
                    jtfThi.setText("");
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
