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


/**
 * Servlet implementation class LoginServlet
 */
//login test
//franco@unisa.it
//pass123?
@WebServlet("/LoginServlet") 
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		
		String email = request.getParameter("email"); 
        String password = request.getParameter("password");

        
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=campi_vuoti"); // Errore campi mancanti
            return;
        }
        
        String hashedPassword = Security.toHash(password);
        UserDAO userDAO = new UserDAO(); 
        UserBean user = null; 
        

        try {
            // login cercando email e pass
            user = userDAO.doRetrieveByEmailPassword(email, hashedPassword); 

            if (user != null) { //se il login è diverso da null (quindi è riuscito)
                
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user); // salviamo userbean in sessione
                //session.setAttribute("isAdmin", user.isIsAdmin()); 
                //salviamo se è un admin, può servire per riempire il catalogo
                response.sendRedirect("catalogo.jsp"); // relink a pagina del catalogo
            } else {
                
                response.sendRedirect("login.jsp?error=invalid_credentials"); //se credenziali errate errore
            }

        } catch (SQLException e) {
            // in caso di errori del db
            e.printStackTrace(); 
            response.sendRedirect("login.jsp?error=db_error");
        }
    }


}