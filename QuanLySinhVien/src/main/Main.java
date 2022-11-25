/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import view.LogInJDialog;
import view.MainJFrame;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        LogInJDialog dialog = new LogInJDialog(null, true);
        dialog.setTitle("Đăng nhập vào ứng dụng");
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
