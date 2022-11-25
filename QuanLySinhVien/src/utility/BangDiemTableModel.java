/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.BangDiem;
import model.SinhVien;

/**
 *
 * @author ASUS
 */
public class BangDiemTableModel {
    public DefaultTableModel setTableBangDiem(List<BangDiem> listItem, String[] listColumn) {
        DefaultTableModel dtm;
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            

        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if (rows > 0) {
            for (int i = 0; i < rows; ++i) {
                BangDiem bd = listItem.get(i);
                obj = new Object[columns];
                obj[0] = (i + 1);
                obj[1] = bd.getMaSinhVien();
                obj[2] = bd.getTenSinhVien();
                obj[3] = bd.getMaHocPhan();
                obj[4] = bd.getTenHocPhan();
                obj[5] = bd.getSoTinChi();
                obj[6] = bd.getDiemcc();
                obj[7] = bd.getDiemkt();
                obj[8] = bd.getDiemth();
                obj[9] = bd.getDiembt();
                obj[10] = bd.getEnd();
                obj[11] = bd.getTK();
                obj[12] = bd.getXL();
                obj[13] = bd.getKQ();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
}
