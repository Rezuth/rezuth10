/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my;

/**
 *
 * @author vALI
 */

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBConnection;

 
public class Server {
    
     private Connection connection;
    
    /**
     *
     * @param us
     * @param pass
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getPhone(String us) throws ClassNotFoundException, SQLException{
    
            String url = "jdbc:mysql://localhost:3306/prototip";
            String uname = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname,
           password);
            Statement instr = con.createStatement();
            String sql = "SELECT * FROM userss";
            ResultSet rs = instr.executeQuery(sql);
            String phone="";
            
            while (rs.next()) {
                if(us.equals(rs.getString("username")))
                {
                    phone=(String)rs.getString("telephone");
                    return phone;
                }
            }
            rs.close();
            instr.close();
            return phone;
    }
    
    
    public void InsertTicket(String us, String tel, String match, String area, int nrTickets, float totSum) throws ClassNotFoundException, SQLException
    {
            String url = "jdbc:mysql://localhost:3306/prototip";
            String uname = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
       
            Connection con = DriverManager.getConnection(url, uname,
            password);
            PreparedStatement statement =(PreparedStatement) con.prepareStatement("INSERT into purchases VALUES(?, ?, ?, ?, ?, ?)");
            statement.setString(1, us);
            statement.setString(2, tel);
            statement.setString(3, match);
            statement.setString(4, area);
            statement.setInt(5, nrTickets);
            statement.setFloat(6, totSum);
            statement.executeUpdate();
            statement.close();
            con.close();
        }
    
    
     
      public void DeleteTicket(String match, String area) throws SQLException
    {
            
            
             connection=DBConnection.getConnection();
        try {
            PreparedStatement prepStmt = 
                    connection.prepareStatement("DELETE FROM tickets WHERE match=? and area=?");
            prepStmt.setString(1, match);
            prepStmt.setString(2, area);
            prepStmt.execute();
   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ;
        
        }
     
    
    
 
        
        
      
        
       
    
}