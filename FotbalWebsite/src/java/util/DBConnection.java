/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




/**
 *
 * @author Andrei
 */
public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/prototip";
                String uname = "root";
                String password = "";
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, uname, password);
                connection.setAutoCommit(false);
            } catch (ClassNotFoundException e) {
                System.err.println("Could not load db driver");
            } catch (SQLException e) {
                System.err.println("Could not open db connction");
            }
        }
      
        
        return connection;
    }

    
    
   

    

}
