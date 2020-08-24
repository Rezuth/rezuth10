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
import java.util.ArrayList;
import java.util.List;
import model.Product;
import util.DBConnection;

/**
 *
 * @author Andrei
 */
public class ProductDAO {
    private Connection connection;
    private static ProductDAO instance;

    public static ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }

    private ProductDAO() {}
    
    public List<Product> getProducts() throws SQLException {
        connection=DBConnection.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement prepStmt = connection.prepareStatement("select  * from products");
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("description"), rs.getFloat("unprice")));
            }
            prepStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
}
