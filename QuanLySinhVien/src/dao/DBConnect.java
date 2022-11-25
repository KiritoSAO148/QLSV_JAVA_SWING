/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DBConnect {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        try{
            Connection cons = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            cons = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4306/qlsv", "root", "");
            return cons;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        System.out.println(c.toString());
        c.close();
    }
}
