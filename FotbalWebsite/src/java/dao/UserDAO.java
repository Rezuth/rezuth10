/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBConnection;
import util.Hash;

public class UserDAO {
    private Connection connection;
    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    private UserDAO() {}
    
    public boolean userExists(String username) throws SQLException{
        connection=DBConnection.getConnection();
         try {
            PreparedStatement prepStmt = 
                    connection.prepareStatement("SELECT * from userss u where u.username = ?");
            prepStmt.setString(1, username);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                rs.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean createUser(String us, String pass, String tel, String email, String country) throws SQLException {
        connection=DBConnection.getConnection();
        try {
            PreparedStatement stmt = 
                    connection.prepareStatement("INSERT into userss(username, password, telephone, email, country) VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, us);
            stmt.setString(2, Hash.getHash(pass));
            stmt.setString(3, tel);
            stmt.setString(4, email);
            stmt.setString(5, country);
            stmt.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean isPasswordCorrect(String username, String password) throws SQLException {
        connection=DBConnection.getConnection();
        try {
            PreparedStatement prepStmt = 
                    connection.prepareStatement("select * from userss u where u.username = ? and u.password = ?");
            prepStmt.setString(1, username);
            prepStmt.setString(2, Hash.getHash(password));
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) 
            {
                rs.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
