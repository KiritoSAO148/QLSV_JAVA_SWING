/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import view.MainJFrame;

/**
 *
 * @author ASUS
 */
public class WindowScreen extends JFrame{
    Timer timer;
    
    public WindowScreen() {
        display();
        
    }
    
    public void display(){
        JWindow window = new JWindow(this);
        window.setSize(800, 500);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        JPanel panel = new JPanel();
        window.add(panel);
        String imagePath = "D:\\Documents\\Pictures\\263-2635963_admin-png.png";
        JLabel label = new JLabel(new ImageIcon(imagePath));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        panel.setBackground(Color.gray);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JProgressBar process = new JProgressBar(0, 100);
        process.setForeground(Color.orange);
        window.add(process, BorderLayout.PAGE_END);
        window.revalidate();
        
        timer = new Timer (100, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = process.getValue();
                if (value == 100){
                    
                    //WindowScreen.this.setVisible(true);
                    window.dispose();
                    MainJFrame f = new MainJFrame();
                    f.setTitle("QUẢN LÝ SINH VIÊN");
                    f.setVisible(true);
                    //f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    
                    timer.stop();
                }else process.setValue(value + 5);
            }
        });
        timer.start();
    }
    
    public static void main(String[] args) {
        new WindowScreen();
    }
}
