/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.HocPhan;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public class HocPhanTableModel {
    public DefaultTableModel setTableHocPhan (List <HocPhan> listItem, String[] listColumn){
        DefaultTableModel dtm;
        dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }
            
            
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if (rows > 0){
            for (int i = 0; i < rows; ++i){
                HocPhan hp = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i + 1);
                obj[1] = hp.getMaHocPhan();
                obj[2] = hp.getTenHocPhan();
                obj[3] = hp.getMoTa();
                obj[4] = hp.getSoTinChi();
                obj[5] = hp.getNgayBatDau();
                obj[6] = hp.getNgayKetThuc();
                obj[7] = hp.isStatus();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
