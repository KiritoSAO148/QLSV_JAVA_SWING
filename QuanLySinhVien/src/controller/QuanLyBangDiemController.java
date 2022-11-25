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
import model.BangDiem;
import model.SinhVien;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.BangDiemService;
import service.BangDiemServiceImpl;
import service.SinhVienService;
import service.SinhVienServiceImpl;
import utility.BangDiemTableModel;
import utility.SinhVienTableModel;
import view.BangDiemJFrame;
import view.BangDiemJFrame1;
import view.BangDiemJPanel;
import view.SinhVienJFrame;
import view.SinhVienJFrame1;
import view.SinhVienJPanel;

/**
 *
 * @author ASUS
 */
public class QuanLyBangDiemController {
    private JPanel jpnView;
    private JButton jbtnEdit;
    private JButton jbtnAdd;
    private JTextField jtfSearch;
    private JButton jbtnPrint;
    
    private BangDiemService bangDiemService = null;
    
    private String[] listColumn = {"STT", "Mã sinh viên", "Họ và tên", "Mã học phần", "Tên học phần", "Số tín chỉ", 
        "CC", "KT", "TH", "BT", "CK", "TK", "XL", "KQ"};
    
    private TableRowSorter <TableModel> rowSorter = null;

    public QuanLyBangDiemController(JPanel jpnView, JButton jbtnEdit, JButton jbtnAdd, JTextField jtfSearch, JButton jbtnPrint) {
        this.jpnView = jpnView;
        this.jbtnEdit = jbtnEdit;
        this.jbtnAdd = jbtnAdd;
        this.jtfSearch = jtfSearch;
        this.jbtnPrint = jbtnPrint;
        this.bangDiemService = new BangDiemServiceImpl();
    }

    public void setTable(){
        List <BangDiem> listItem = bangDiemService.getList();
        
        DefaultTableModel model = new BangDiemTableModel().setTableBangDiem(listItem, listColumn);
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
        
        table.getColumnModel().getColumn(2).setMinWidth(150);
        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        
        table.getColumnModel().getColumn(3).setMinWidth(80);
        table.getColumnModel().getColumn(3).setMaxWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        
        table.getColumnModel().getColumn(4).setMinWidth(200);
        table.getColumnModel().getColumn(4).setMaxWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    
                    BangDiem bd = new BangDiem();
                    bd.setMaSinhVien(model.getValueAt(selectedRowIndex, 1) != null ?
                            model.getValueAt(selectedRowIndex, 1).toString() : "");
                    bd.setTenSinhVien(model.getValueAt(selectedRowIndex, 2) != null ?
                            model.getValueAt(selectedRowIndex, 2).toString() : "");
                    bd.setMaHocPhan(model.getValueAt(selectedRowIndex, 3) != null ?
                            model.getValueAt(selectedRowIndex, 3).toString() : "");
                    bd.setTenHocPhan(model.getValueAt(selectedRowIndex, 4) != null ?
                            model.getValueAt(selectedRowIndex, 4).toString() : "");
                    bd.setSoTinChi(Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString()));
                    bd.setDiemcc(Double.parseDouble(model.getValueAt(selectedRowIndex, 6).toString()));
                    bd.setDiemkt(Double.parseDouble(model.getValueAt(selectedRowIndex, 7).toString()));
                    bd.setDiemth(Double.parseDouble(model.getValueAt(selectedRowIndex, 8).toString()));
                    bd.setDiembt(Double.parseDouble(model.getValueAt(selectedRowIndex, 9).toString()));
                    bd.setEnd(Double.parseDouble(model.getValueAt(selectedRowIndex, 10).toString()));
                    
                    BangDiemJFrame f = new BangDiemJFrame(bd);
                    f.setTitle("Thông tin bảng điểm");
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
                BangDiemJFrame f = new BangDiemJFrame(new BangDiem());
                f.setTitle("Thông tin bảng điểm");
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
                BangDiemJFrame1 f = new BangDiemJFrame1(new BangDiem());
                f.setTitle("Thông tin bảng điểm");
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
                XSSFSheet sheet = workbook.createSheet("Bảng Ðiểm");
                
                XSSFRow row = null;
                Cell cell = null;
                
                row = sheet.createRow(3);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã sinh viên");
                
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Tên sinh viên");
                
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Mã học phần");
                
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Tên học phần");
                
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Số tín chỉ");
                
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("CC");
                
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("KT");
                
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue("TH");
                
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue("BT");
                
                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue("CK");
                
                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue("TK");
                
                cell = row.createCell(12, CellType.STRING);
                cell.setCellValue("XL");
                
                cell = row.createCell(13, CellType.STRING);
                cell.setCellValue("KQ");
                
                List <BangDiem> listItem = bangDiemService.getList();
                
                if (listItem != null){
                    FileOutputStream fos = null;
                    try {
                        for (int i = 0; i < listItem.size(); ++i){
                            BangDiem bd = listItem.get(i);
                            
                            row = sheet.createRow(i + 4);
                            
                            cell = row.createCell(0, CellType.NUMERIC);
                            cell.setCellValue(i + 1);
                            
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(bd.getMaSinhVien());
                            
                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(bd.getTenSinhVien());
                            
                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(bd.getMaHocPhan());
                            
                            cell = row.createCell(4, CellType.STRING);
                            cell.setCellValue(bd.getTenHocPhan());
                            
                            cell = row.createCell(5, CellType.STRING);
                            cell.setCellValue(bd.getSoTinChi());
                            
                            cell = row.createCell(6, CellType.STRING);
                            cell.setCellValue(bd.getDiemcc() + "");
                            
                            cell = row.createCell(7, CellType.STRING);
                            cell.setCellValue(bd.getDiemkt() + "");
                            
                            cell = row.createCell(8, CellType.STRING);
                            cell.setCellValue(bd.getDiemth() + "");
                            
                            cell = row.createCell(9, CellType.STRING);
                            cell.setCellValue(bd.getDiembt() + "");
                            
                            cell = row.createCell(10, CellType.STRING);
                            cell.setCellValue(bd.getEnd() + "");
                            
                            cell = row.createCell(11, CellType.STRING);
                            cell.setCellValue(bd.getTK() + "");
                            
                            cell = row.createCell(12, CellType.STRING);
                            cell.setCellValue(bd.getXL());
                            
                            cell = row.createCell(13, CellType.STRING);
                            cell.setCellValue(bd.getKQ());
                        }   
                        File file = new File ("D:\\Documents\\NetBeansProject\\Danh sách bảng điểm.xlsx");
                        fos = new FileOutputStream(file);
                        workbook.write(fos);
                        BangDiemJPanel panel = new BangDiemJPanel();
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
