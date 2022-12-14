/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hafid
 */
public class Koneksi {
    private static Connection connection;
    
    public static Connection GetConnection()throws SQLException {
        if (connection == null) {
            new Driver();
            
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/toko_kue","root","");
        } 
        return connection;
    }
}
