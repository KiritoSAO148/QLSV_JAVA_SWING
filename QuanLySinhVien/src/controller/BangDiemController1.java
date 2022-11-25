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
public class BangDiemController1 {

    private JButton jbtnUpdate;
    private JButton jbtnSearch;
    private JButton jbtnDelete;
    private JTextField jtfMaSinhVien1;
    private JTextField jtfHoTen1;
    private JTextField jtfMaHocPhan1;
    private JTextField jtfTenHocPhan1;
    private JTextField jtfSoTinChi1;
    private JTextField jtfCC1;
    private JTextField jtfKT1;
    private JTextField jtfTH1;
    private JTextField jtfBT1;
    private JTextField jtfThi1;
    private JLabel jlbMsg1;

    private BangDiem bd = null;
    private BangDiemService bangDiemService = null;

    public BangDiemController1(JButton jbtnUpdate, JButton jbtnSearch, JButton jbtnDelete, JTextField jtfMaSinhVien1, JTextField jtfHoTen1, JTextField jtfMaHocPhan1, JTextField jtfTenHocPhan1, JTextField jtfSoTinChi1, JTextField jtfCC1, JTextField jtfKT1, JTextField jtfTH1, JTextField jtfBT1, JTextField jtfThi1, JLabel jlbMsg1) {
        this.jbtnUpdate = jbtnUpdate;
        this.jbtnSearch = jbtnSearch;
        this.jbtnDelete = jbtnDelete;
        this.jtfMaSinhVien1 = jtfMaSinhVien1;
        this.jtfHoTen1 = jtfHoTen1;
        this.jtfMaHocPhan1 = jtfMaHocPhan1;
        this.jtfTenHocPhan1 = jtfTenHocPhan1;
        this.jtfSoTinChi1 = jtfSoTinChi1;
        this.jtfCC1 = jtfCC1;
        this.jtfKT1 = jtfKT1;
        this.jtfTH1 = jtfTH1;
        this.jtfBT1 = jtfBT1;
        this.jtfThi1 = jtfThi1;
        this.jlbMsg1 = jlbMsg1;

        this.bangDiemService = new BangDiemServiceImpl();
    }

    public void setView(BangDiem bd) {
        this.bd = bd;
        jtfMaSinhVien1.setText(bd.getMaSinhVien());
        jtfHoTen1.setText(bd.getTenSinhVien());
        jtfMaHocPhan1.setText(bd.getMaHocPhan());
        jtfTenHocPhan1.setText(bd.getTenHocPhan());
        jtfSoTinChi1.setText(bd.getSoTinChi() + "");
        jtfCC1.setText(bd.getDiemcc() + "");
        jtfKT1.setText(bd.getDiemkt() + "");
        jtfTH1.setText(bd.getDiemth() + "");
        jtfBT1.setText(bd.getDiembt() + "");
        jtfThi1.setText(bd.getEnd() + "");
    }

    public void setEvent() {
        jbtnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BangDiemJFrame f = new BangDiemJFrame(bd);
                if (jtfMaSinhVien1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    BangDiem bd = bangDiemService.findByMsv(jtfMaSinhVien1.getText());
                    if (bd != null) {
                        jtfHoTen1.setText(bd.getTenSinhVien());
                        jtfMaHocPhan1.setText(bd.getMaHocPhan());
                        jtfTenHocPhan1.setText(bd.getTenHocPhan());
                        jtfSoTinChi1.setText(bd.getSoTinChi() + "");
                        jtfCC1.setText(bd.getDiemcc() + "");
                        jtfKT1.setText(bd.getDiemkt()+ "");
                        jtfTH1.setText(bd.getDiemth()+ "");
                        jtfBT1.setText(bd.getDiembt()+ "");
                        jtfThi1.setText(bd.getEnd()+ "");
                        JOptionPane.showConfirmDialog(f, "Tìm thấy bảng điểm trong CSDL!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        jtfHoTen1.setText("");
                        jtfMaHocPhan1.setText("");
                        jtfTenHocPhan1.setText("");
                        jtfSoTinChi1.setText("");
                        jtfCC1.setText("");
                        jtfKT1.setText("");
                        jtfTH1.setText("");
                        jtfBT1.setText("");
                        jtfThi1.setText("");
                        JOptionPane.showConfirmDialog(f, "Không tìm thấy bảng điểm trong CSDL!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
                BangDiemJFrame f = new BangDiemJFrame(bd);
                if (jtfMaSinhVien1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    BangDiem bd = new BangDiem();
                    bd.setMaSinhVien(jtfMaSinhVien1.getText());
                    bd.setTenSinhVien(jtfHoTen1.getText());
                    bd.setMaHocPhan(jtfMaHocPhan1.getText());
                    bd.setTenHocPhan(jtfTenHocPhan1.getText());
                    bd.setSoTinChi(Integer.parseInt(jtfSoTinChi1.getText()));
                    bd.setDiemcc(Double.parseDouble(jtfCC1.getText()));
                    bd.setDiemkt(Double.parseDouble(jtfKT1.getText()));
                    bd.setDiemth(Double.parseDouble(jtfTH1.getText()));
                    bd.setDiembt(Double.parseDouble(jtfBT1.getText()));
                    bd.setEnd(Double.parseDouble(jtfThi1.getText()));
                    bangDiemService.update(bd);
                    JOptionPane.showConfirmDialog(f, "Cập nhật dữ liệu thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaSinhVien1.setText("");
                    jtfHoTen1.setText("");
                    jtfMaHocPhan1.setText("");
                    jtfTenHocPhan1.setText("");
                    jtfSoTinChi1.setText("");
                    jtfCC1.setText("");
                    jtfKT1.setText("");
                    jtfTH1.setText("");
                    jtfBT1.setText("");
                    jtfThi1.setText("");
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
                BangDiemJFrame f = new BangDiemJFrame(bd);
                if (jtfMaSinhVien1.getText().length() == 0) {
                    JOptionPane.showConfirmDialog(f, "Vui lòng nhập dữ liệu bắt buộc!", "WARNING", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                } else {
                    bangDiemService.delete(jtfMaSinhVien1.getText());
                    JOptionPane.showConfirmDialog(f, "Bảng điểm được xóa khỏi CSDL thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    jtfMaSinhVien1.setText("");
                    jtfHoTen1.setText("");
                    jtfMaHocPhan1.setText("");
                    jtfTenHocPhan1.setText("");
                    jtfSoTinChi1.setText("");
                    jtfCC1.setText("");
                    jtfKT1.setText("");
                    jtfTH1.setText("");
                    jtfBT1.setText("");
                    jtfThi1.setText("");
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
