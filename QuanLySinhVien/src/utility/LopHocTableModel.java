/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.LopHoc;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public class LopHocTableModel {

    public DefaultTableModel setTableLopHoc(List<LopHoc> listItem, String[] listColumn) {
        DefaultTableModel dtm;
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }

        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if (rows > 0) {
            for (int i = 0; i < rows; ++i) {
                LopHoc lh = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i + 1);
                obj[1] = lh.getMaLopHoc();
                obj[2] = lh.getMaHocPhan();
                obj[3] = lh.getMaSinhVien();
                obj[4] = lh.getNgayDangKy();
                obj[5] = lh.isStatus();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
