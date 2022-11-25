/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import chuanhoa.ChuanHoaMatKhau;
import chuanhoa.ChuanHoaTenDangNhap;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import login.WindowScreen;
import model.TaiKhoan;
import service.TaiKhoanService;
import service.TaiKhoanServiceImpl;
import view.MainJFrame;
import view.SinhVienJFrame;

/**
 *
 * @author ASUS
 */
public class TaiKhoanController {

    private JDialog dialog;
    private JButton jbtnSubmit;
    private JTextField jtfTenDangNhap;
    private JPasswordField jpfMatKhau;
    private JLabel jlbMsg;

    private TaiKhoanService taiKhoanService = null;

    public TaiKhoanController(JDialog dialog, JButton jbtnSubmit, JTextField jtfTenDangNhap, JPasswordField jpfMatKhau, JLabel jlbMsg) {
        this.dialog = dialog;
        this.jbtnSubmit = jbtnSubmit;
        this.jtfTenDangNhap = jtfTenDangNhap;
        this.jpfMatKhau = jpfMatKhau;
        this.jlbMsg = jlbMsg;

        taiKhoanService = new TaiKhoanServiceImpl();
    }

    public void setEvent() {
        jbtnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = jtfTenDangNhap.getText();
                char[] res = jpfMatKhau.getPassword();
                String password = "";
                for (char x : res) password += x;
                if (username.length() == 0 || password.length() == 0) {
                    jlbMsg.setText("Vui lòng nhập thông tin bắt buộc");
                } else {
                    TaiKhoan taiKhoan = taiKhoanService.login(username, password);
                    if (taiKhoan == null) {
                        jlbMsg.setText("Sai thông tin đăng nhập hoặc mật khẩu");
                    } else {
                        if (!taiKhoan.isTinh_trang()) {
                            jlbMsg.setText("Tài khoản đang bị tạm khóa");
                        } else {
                            WindowScreen window = new WindowScreen();
                            window.setVisible(true);
//                            MainJFrame f = new MainJFrame();
//                            f.setTitle("QUẢN LÝ SINH VIÊN");
//                            f.setVisible(true);
                            //f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            dialog.dispose();
                        }
                    }
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
