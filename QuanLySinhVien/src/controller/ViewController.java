/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.DanhMucBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.BangDiemJPanel;
import view.HocPhanJPanel;
import view.LogInJDialog;
import view.LopHocJPanel;
import view.MainJFrame;
import view.SinhVienJPanel;
import view.ThongKeJPanel;
import view.TrangChuJPanel;

/**
 *
 * @author ASUS
 */
public class ViewController {
    private JPanel root;
    private String kindSelected = "";
    
    private List<DanhMucBean> listItem;

    public ViewController(JPanel root) {
        this.root = root;
    }

    public void setView (JPanel jpnItem, JLabel jlbItem){
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(255, 191, 128));
        jlbItem.setBackground(new Color(255, 191, 128));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }
    
    public void setEvent(List<DanhMucBean> listItem){
        this.listItem = listItem;
        for (DanhMucBean item : listItem){
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }
    
    class LabelEvent implements MouseListener{

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind){
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "SinhVien":
                    node = new SinhVienJPanel();
                    break;
                case "HocPhan":
                    node = new HocPhanJPanel();
                    break;
                case "LopHoc":
                    node = new LopHocJPanel();
                    break;
                case "BangDiem":
                    node = new BangDiemJPanel();
                    break;
                case "ThongKe":
                    node = new ThongKeJPanel();
                    break;
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(51, 133, 255));
            jlbItem.setBackground(new Color(51, 133, 255));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(51, 133, 255));
            jlbItem.setBackground(new Color(51, 133, 255));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(0, 179, 0));
                jlbItem.setBackground(new Color(0, 179, 0));
            }
        }
        
    }
    
    public void setChangeBackground (String kind){
        for (DanhMucBean item : listItem){
            if (item.getKind().equalsIgnoreCase(kind)){
                item.getJpn().setBackground(new Color(51, 133, 255));
                item.getJlb().setBackground(new Color(51, 133, 255));
            }else{
                item.getJpn().setBackground(new Color(0, 179, 0));
                item.getJlb().setBackground(new Color(0, 179, 0));
            }
        }
    }
}
