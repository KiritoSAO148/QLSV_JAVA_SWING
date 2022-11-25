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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.SinhVien;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.SinhVienService;
import service.SinhVienServiceImpl;
import utility.SinhVienTableModel;
import view.MainJFrame;
import view.SinhVienJFrame;
import view.SinhVienJFrame1;
import view.SinhVienJPanel;

/**
 *
 * @author ASUS
 */
public class QuanLySinhVienController {
    private JPanel jpnView;
    private JButton jbtnEdit;
    private JButton jbtnAdd;
    private JTextField jtfSearch;
    private JButton jbtnPrint;
    
    private SinhVienService sinhVienService = null;
    
    private String[] listColumn = {"STT", "Mã sinh viên", "Họ và tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Ngành", "GPA", "Tình trạng"};
    
    private TableRowSorter <TableModel> rowSorter = null;

    public QuanLySinhVienController(JPanel jpnView, JButton jbtnEdit, JButton jbtnAdd, JTextField jtfSearch, JButton jbtnPrint) {
        this.jpnView = jpnView;
        this.jbtnEdit = jbtnEdit;
        this.jbtnAdd = jbtnAdd;
        this.jtfSearch = jtfSearch;
        this.jbtnPrint = jbtnPrint;
        this.sinhVienService = new SinhVienServiceImpl();
    }

    public void setTable(){
        List <SinhVien> listItem = sinhVienService.getList();
        
        DefaultTableModel model = new SinhVienTableModel().setTableSinhVien(listItem, listColumn);
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
        
        table.getColumnModel().getColumn(2).setMinWidth(150);
        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    
                    SinhVien sv = new SinhVien();
                    sv.setMaSinhVien(model.getValueAt(selectedRowIndex, 1) != null ?
                            model.getValueAt(selectedRowIndex, 1).toString() : "");
                    sv.setHoTen(model.getValueAt(selectedRowIndex, 2) != null ?
                            model.getValueAt(selectedRowIndex, 2).toString() : "");
                    sv.setNgaySinh((Date) model.getValueAt(selectedRowIndex, 3));
                    sv.setGioiTinh(model.getValueAt(selectedRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                    sv.setSoDienThoai(model.getValueAt(selectedRowIndex, 5) != null ?
                            model.getValueAt(selectedRowIndex, 5).toString() : "");
                    sv.setDiaChi(model.getValueAt(selectedRowIndex, 6) != null ?
                            model.getValueAt(selectedRowIndex, 6).toString() : "");
                    sv.setNganh(model.getValueAt(selectedRowIndex, 7) != null ?
                            model.getValueAt(selectedRowIndex, 7).toString() : "");
                    sv.setGpa(model.getValueAt(selectedRowIndex, 8) != null ?
                            model.getValueAt(selectedRowIndex, 8).toString() : "");
                    sv.setStatus((boolean) model.getValueAt(selectedRowIndex, 9));
                    
                    SinhVienJFrame f = new SinhVienJFrame(sv);
                    f.setTitle("Thông tin sinh viên");
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
                SinhVienJFrame f = new SinhVienJFrame(new SinhVien());
                f.setTitle("Thông tin sinh viên");
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
                SinhVienJFrame1 f = new SinhVienJFrame1(new SinhVien());
                f.setTitle("Thông tin sinh viên");
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
                XSSFSheet sheet = workbook.createSheet("Sinh Viên");
                
                XSSFRow row = null;
                Cell cell = null;
                
                row = sheet.createRow(3);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã sinh viên");
                
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Họ và tên");
                
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Ngày sinh");
                
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Giới tính");
                
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Số điện thoại");
                
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Địa chỉ");
                
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Ngành");
                
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue("GPA");
                
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue("Tình trạng");
                
                List <SinhVien> listItem = sinhVienService.getList();
                
                if (listItem != null){
                    FileOutputStream fos = null;
                    try {
                        for (int i = 0; i < listItem.size(); ++i){
                            SinhVien sv = listItem.get(i);
                            
                            row = sheet.createRow(i + 4);
                            
                            cell = row.createCell(0, CellType.NUMERIC);
                            cell.setCellValue(i + 1);
                            
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(sv.getMaSinhVien());
                            
                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(sv.getHoTen());
                            
                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(sv.getNgaySinh().toString());
                            
                            cell = row.createCell(4, CellType.STRING);
                            cell.setCellValue(sv.getGioiTinh() ? "Nam" : "Nữ");
                            
                            cell = row.createCell(5, CellType.STRING);
                            cell.setCellValue(sv.getSoDienThoai());
                            
                            cell = row.createCell(6, CellType.STRING);
                            cell.setCellValue(sv.getDiaChi());
                            
                            cell = row.createCell(7, CellType.STRING);
                            cell.setCellValue(sv.getNganh());
                            
                            cell = row.createCell(8, CellType.STRING);
                            cell.setCellValue(sv.getGpa());
                            
                            cell = row.createCell(9, CellType.STRING);
                            cell.setCellValue(sv.getStatus() ? "Hoạt động" : "Không hoạt động");
                            
                            
                        }   
                        File file = new File ("D:\\Documents\\NetBeansProject\\Danh sách sinh viên.xlsx");
                        fos = new FileOutputStream(file);
                        workbook.write(fos);
                        SinhVienJPanel panel = new SinhVienJPanel();
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
