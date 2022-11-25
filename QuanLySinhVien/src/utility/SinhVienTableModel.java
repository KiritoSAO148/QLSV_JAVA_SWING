/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public class SinhVienTableModel {

    public DefaultTableModel setTableSinhVien(List<SinhVien> listItem, String[] listColumn) {
        DefaultTableModel dtm;
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 9 ? Boolean.class : String.class;
            }

        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if (rows > 0) {
            for (int i = 0; i < rows; ++i) {
                SinhVien sv = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i + 1);
                obj[1] = sv.getMaSinhVien();
                obj[2] = sv.getHoTen();
                obj[3] = sv.getNgaySinh();
                obj[4] = sv.getGioiTinh() == true ? "Nam" : "Nữ";
                obj[5] = sv.getSoDienThoai();
                obj[6] = sv.getDiaChi();
                obj[7] = sv.getNganh();
                obj[8] = sv.getGpa();
                obj[9] = sv.getStatus();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
