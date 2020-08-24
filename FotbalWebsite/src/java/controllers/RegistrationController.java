package controllers;

import dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Checks if the username already exists. If exists the login page is reloaded.
 * If not the user is added in the database and the home page is reloaded
 */
//@WebServlet(name = "registrationController")
public class RegistrationController extends HttpServlet {
    UserDAO userDAO=UserDAO.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        RequestDispatcher rd;
            String error ="";
            boolean hasErrors=false;
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String rpassword=request.getParameter("rpassword");
            String telephone=request.getParameter("telephone");
            String email=request.getParameter("email");
            String country=request.getParameter("country");
            if(userDAO.userExists(username)){
                hasErrors=true;
                error="Username already exists";
            }
            else
            {
                if(!password.equals(rpassword))
                {
                    hasErrors=true;
                    error="Passwords do not match";
                }   
            }
            
            if(hasErrors){
            request.setAttribute("ERRORS", error);
            rd = request.getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
            }
            else{
            userDAO.createUser(username, password, telephone, email, country);
            rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
            }
                


        
    }
}
