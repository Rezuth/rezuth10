/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import dao.ProductDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import model.Product;


public class contextListener implements ServletContextListener {
    ProductDAO productDAO=ProductDAO.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Product> products = null;
        try {
            products = productDAO.getProducts();
        } catch (SQLException ex) {
            Logger.getLogger(contextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        sce.getServletContext().setAttribute("PRODUCTS", products);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
