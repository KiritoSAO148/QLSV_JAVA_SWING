/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.HocPhan;
import model.LopHoc;
import model.SinhVien;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.LopHocService;
import service.LopHocServiceImpl;
import service.SinhVienService;
import service.SinhVienServiceImpl;
import utility.LopHocTableModel;
import utility.SinhVienTableModel;
import view.HocPhanJPanel;
import view.LopHocJFrame;
import view.LopHocJFrame1;
import view.LopHocJPanel;
import view.SinhVienJFrame;
import view.SinhVienJFrame1;

/**
 *
 * @author ASUS
 */
public class QuanLyLopHocController {
    private JPanel jpnView;
    private JButton jbtnEdit;
    private JButton jbtnAdd;
    private JTextField jtfSearch;
    private JButton jbtnPrint;
    
    private LopHocService lopHocService = null;
    
    private String[] listColumn = {"STT", "Mã lớp học", "Mã học phần", "Mã sinh viên", "Ngày đăng ký", "Tình trạng"};
    
    private TableRowSorter <TableModel> rowSorter = null;

    public QuanLyLopHocController(JPanel jpnView, JButton jbtnEdit, JButton jbtnAdd, JTextField jtfSearch, JButton jbtnPrint) {
        this.jpnView = jpnView;
        this.jbtnEdit = jbtnEdit;
        this.jbtnAdd = jbtnAdd;
        this.jtfSearch = jtfSearch;
        this.jbtnPrint = jbtnPrint;
        
        lopHocService = new LopHocServiceImpl();
    }

    public void setTable(){
        List <LopHoc> listItem = lopHocService.getList();
        
        DefaultTableModel model = new LopHocTableModel().setTableLopHoc(listItem, listColumn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               
            }
        });
        
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(1).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    
                    LopHoc lh = new LopHoc();
                    lh.setMaLopHoc(model.getValueAt(selectedRowIndex, 1) != null ?
                            model.getValueAt(selectedRowIndex, 1).toString() : "");
                    lh.setMaHocPhan(model.getValueAt(selectedRowIndex, 2) != null ?
                            model.getValueAt(selectedRowIndex, 2).toString() : "");
                    lh.setMaSinhVien(model.getValueAt(selectedRowIndex, 3) != null ?
                            model.getValueAt(selectedRowIndex, 2).toString() : "");
                    lh.setNgayDangKy((Date) model.getValueAt(selectedRowIndex, 4));
                    lh.setStatus((boolean) model.getValueAt(selectedRowIndex, 5));
                    
                    LopHocJFrame f = new LopHocJFrame(lh);
                    f.setTitle("Thông tin lớp học");
                    f.setResizable(false);
                    f.setLocationRelativeTo(null);
                    f.setVisible(true);
                }
            }
        });
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.getViewport().add(table);
        jScrollPane.setPreferredSize(new Dimension(1300, 400));
        
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(jScrollPane);
        jpnView.validate();
        jpnView.repaint();
    }
    
    public void setEvent(){
        jbtnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LopHocJFrame f = new LopHocJFrame(new LopHoc());
                f.setTitle("Thông tin lớp học");
                f.setLocationRelativeTo(null);
                f.setResizable(false);
                f.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtnAdd.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbtnAdd.setBackground(new Color(112, 219, 112));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                jbtnAdd.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jbtnAdd.setBackground(new Color(102, 153, 255));
            }
            
            
        });
        
        jbtnEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LopHocJFrame1 f = new LopHocJFrame1(new LopHoc());
                f.setTitle("Thông tin lớp học");
                f.setLocationRelativeTo(null);
                f.setResizable(false);
                f.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtnEdit.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbtnEdit.setBackground(new Color(112, 219, 112));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                jbtnEdit.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jbtnEdit.setBackground(new Color(102, 153, 255));
            }
            
            
        });
        
        jbtnPrint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Lớp học");
                
                XSSFRow row = null;
                Cell cell = null;
                
                row = sheet.createRow(3);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã lớp học");
                
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Mã học phần");
                
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Mã sinh viên");
                
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Ngày đăng ký");
                
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Tình trạng");
                
                
                
                
                List <LopHoc> listItem = lopHocService.getList();
                
                if (listItem != null){
                    FileOutputStream fos = null;
                    try {
                        for (int i = 0; i < listItem.size(); ++i){
                            LopHoc lh = listItem.get(i);
                            
                            row = sheet.createRow(i + 4);
                            
                            cell = row.createCell(0, CellType.NUMERIC);
                            cell.setCellValue(i + 1);
                            
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(lh.getMaLopHoc());
                            
                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(lh.getMaHocPhan());
                            
                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(lh.getMaSinhVien());
                            
                            
                            cell = row.createCell(4, CellType.STRING);
                            cell.setCellValue(lh.getNgayDangKy().toString());
                            
                            cell = row.createCell(5, CellType.STRING);
                            cell.setCellValue(lh.isStatus() ? "Hoạt động" : "Không hoạt động");
                            
                            
                            
                            
                        }   
                        File file = new File ("D:\\Documents\\NetBeansProject\\Danh sách lớp học.xlsx");
                        fos = new FileOutputStream(file);
                        workbook.write(fos);
                        LopHocJPanel panel = new LopHocJPanel();
                        JOptionPane.showConfirmDialog(panel, "Xuất dữ liệu thành công!", "MESSAGE", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        fos.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } 
                    
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtnPrint.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbtnPrint.setBackground(new Color(112, 219, 112));
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                jbtnPrint.setBackground(new Color(102, 153, 255));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                jbtnPrint.setBackground(new Color(102, 153, 255));
            }
            
            
        });
        
    }
}
