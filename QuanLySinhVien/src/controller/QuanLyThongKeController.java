/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.HocPhanBean;
import bean.LopHocBean;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import model.HocPhan;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import service.ThongKeService;
import service.ThongKeServiceImpl;

/**
 *
 * @author ASUS
 */
public class QuanLyThongKeController {
    private ThongKeService thongKeService = null;

    public QuanLyThongKeController() {
        thongKeService = new ThongKeServiceImpl();
    }
    
    public void setDataToChart (JPanel jpnItem){
        List <LopHocBean> listItem = thongKeService.getListByLopHoc();
        if (listItem != null){
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (LopHocBean item : listItem){
                dataset.addValue(item.getSoLuongSinhVien(), "Sinh viên", item.getNgayDangKy());
            }
            
            JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ SỐ LƯỢNG SINH VIÊN ĐĂNG KÝ", 
                    "Thời gian", "Số lượng", dataset);
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));
            
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
    }
    
    public void setDataToChart2 (JPanel jpnItem){
        List <HocPhanBean> listItem = thongKeService.getListByHocPhan();
        if (listItem != null){
            TaskSeriesCollection ds = new TaskSeriesCollection();
            TaskSeries taskSeries;
            Task task;
            for (HocPhanBean item : listItem){
                taskSeries = new TaskSeries(item.getTenHocPhan());
                task = new Task(item.getTenHocPhan(), item.getNgayBatDau(), item.getNgayKetThuc());
                taskSeries.add(task);
                ds.add(taskSeries);
            }
            
            JFreeChart chart = ChartFactory.createGanttChart("THỐNG KÊ TÌNH TRẠNG HỌC PHẦN", 
                    "Khóa học", "Thời gian", ds);
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));
            
            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
    }
}
