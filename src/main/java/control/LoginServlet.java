package control; 

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UserBean;
import model.UserDAO; 

//login test
//franco@unisa.it
//pass123?

@WebServlet("/LoginServlet") 
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("currentUser") != null) {
            response.sendRedirect("index.jsp");
            return;
        }
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email"); 
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("login.jsp?error=campi_vuoti");
            return;
        }
        
        String hashedPassword = Security.toHash(password);
        UserDAO userDAO = new UserDAO(); 
        
        try {
            UserBean user = userDAO.doRetrieveByEmailPassword(email, hashedPassword); 
            
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);
                session.setMaxInactiveInterval(2 * 60 * 60);
                
                if (user.isAdmin()) {
                    response.sendRedirect("admin/dashboard.jsp");
                } else {
                    response.sendRedirect("catalogo.jsp");
                }
            } else {
                response.sendRedirect("login.jsp?error=invalid_credentials");
            }

        } catch (SQLException e) {
            response.sendRedirect("login.jsp?error=db_error");
        }
    }
}

