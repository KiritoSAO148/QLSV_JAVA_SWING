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
import model.SinhVien;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.HocPhanService;
import service.HocPhanServiceImpl;
import service.SinhVienService;
import service.SinhVienServiceImpl;
import utility.HocPhanTableModel;
import utility.SinhVienTableModel;
import view.HocPhanJFrame;
import view.HocPhanJFrame1;
import view.HocPhanJPanel;
import view.SinhVienJFrame;
import view.SinhVienJFrame1;

/**
 *
 * @author ASUS
 */
public class QuanLyHocPhanController {
    private JPanel jpnView;
    private JButton jbtnEdit;
    private JButton jbtnAdd;
    private JTextField jtfSearch;
    private JButton jbtnPrint;
    
    private HocPhanService hocPhanService = null;
    
    private String[] listColumn = {"STT", "Mã học phần", "Tên học phần", "Mô tả", "Số tín chỉ", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng"};
    
    private TableRowSorter <TableModel> rowSorter = null;

    public QuanLyHocPhanController(JPanel jpnView, JButton jbtnEdit, JButton jbtnAdd, JTextField jtfSearch, JButton jbtnPrint) {
        this.jpnView = jpnView;
        this.jbtnEdit = jbtnEdit;
        this.jbtnAdd = jbtnAdd;
        this.jtfSearch = jtfSearch;
        this.jbtnPrint = jbtnPrint;
        hocPhanService = new HocPhanServiceImpl();
    }

    public void setTable(){
        List <HocPhan> listItem = hocPhanService.getList();
        
        DefaultTableModel model = new HocPhanTableModel().setTableHocPhan(listItem, listColumn);
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
        
        table.getColumnModel().getColumn(2).setMinWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    
                    HocPhan hp = new HocPhan();
                    hp.setMaHocPhan(model.getValueAt(selectedRowIndex, 1) != null ?
                            model.getValueAt(selectedRowIndex, 1).toString() : "");
                    hp.setTenHocPhan(model.getValueAt(selectedRowIndex, 2) != null ?
                            model.getValueAt(selectedRowIndex, 2).toString() : "");
                    hp.setMoTa(model.getValueAt(selectedRowIndex, 3) != null ?
                            model.getValueAt(selectedRowIndex, 3).toString() : "");
                    hp.setSoTinChi(Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString()));
                    hp.setNgayBatDau((Date) model.getValueAt(selectedRowIndex, 5));
                    hp.setNgayKetThuc((Date) model.getValueAt(selectedRowIndex, 6));
                    hp.setStatus((boolean) model.getValueAt(selectedRowIndex, 7));
                    
                    HocPhanJFrame f = new HocPhanJFrame(hp);
                    f.setTitle("Thông tin học phần");
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
                HocPhanJFrame f = new HocPhanJFrame(new HocPhan());
                f.setTitle("Thông tin học phần");
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
                HocPhanJFrame1 f = new HocPhanJFrame1(new HocPhan());
                f.setTitle("Thông tin học phần");
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
                XSSFSheet sheet = workbook.createSheet("Học phần");
                
                XSSFRow row = null;
                Cell cell = null;
                
                row = sheet.createRow(3);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã học phần");
                
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Tên học phần");
                
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Mô tả");
                
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Số tín chỉ");
                
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Ngày bắt đầu");
                
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Ngày kết thúc");
                
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Tình trạng");
                
                
                
                List <HocPhan> listItem = hocPhanService.getList();
                
                if (listItem != null){
                    FileOutputStream fos = null;
                    try {
                        for (int i = 0; i < listItem.size(); ++i){
                            HocPhan hp = listItem.get(i);
                            
                            row = sheet.createRow(i + 4);
                            
                            cell = row.createCell(0, CellType.NUMERIC);
                            cell.setCellValue(i + 1);
                            
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(hp.getMaHocPhan());
                            
                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(hp.getTenHocPhan());
                            
                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(hp.getMoTa());
                            
                            cell = row.createCell(4, CellType.NUMERIC);
                            cell.setCellValue(hp.getSoTinChi());
                            
                            cell = row.createCell(5, CellType.STRING);
                            cell.setCellValue(hp.getNgayBatDau().toString());
                            
                            cell = row.createCell(6, CellType.STRING);
                            cell.setCellValue(hp.getNgayKetThuc().toString());
                            
                            
                            
                            cell = row.createCell(7, CellType.STRING);
                            cell.setCellValue(hp.isStatus()? "Hoạt động" : "Không hoạt động");
                            
                            
                        }   
                        File file = new File ("D:\\Documents\\NetBeansProject\\Danh sách học phần.xlsx");
                        fos = new FileOutputStream(file);
                        workbook.write(fos);
                        HocPhanJPanel panel = new HocPhanJPanel();
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
