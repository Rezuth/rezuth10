package controllers;

import dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rfischer on 14.03.16.
 */
public class LoginController extends HttpServlet {
    UserDAO userDAO=UserDAO.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
String username=request.getParameter("username");
String password=request.getParameter("password");
if(userDAO.isPasswordCorrect(username, password)){
            // user exists
            request.getSession().setAttribute("USER", username);
            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        } else {
            // does not exist
            String error="User does not exist or password is incorrect";
            request.setAttribute("ERRORS", error);
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }


    }
}
