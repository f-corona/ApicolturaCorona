package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserBean;
import model.UserDAO;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    public RegistrazioneServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect al form di registrazione
        response.sendRedirect("register.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confermaPassword = request.getParameter("confermaPassword");
        String telefono = request.getParameter("telefono");
        String indirizzoSpedizione = request.getParameter("indirizzoSpedizione");
        String cittaSpedizione = request.getParameter("cittaSpedizione");
        String capSpedizione = request.getParameter("capSpedizione");
        String provinciaSpedizione = request.getParameter("provinciaSpedizione");
        
        UserDAO userDAO = new UserDAO();
        
        if (nome == null || cognome == null || email == null || password == null || 
            confermaPassword == null || indirizzoSpedizione == null || 
            cittaSpedizione == null || capSpedizione == null || provinciaSpedizione == null) {
            response.sendRedirect("register.jsp?error=missing_fields");
            return; 
        }

        if (!password.equals(confermaPassword)) {
            response.sendRedirect("register.jsp?error=password_mismatch");
            return;
        }
        
        UserBean user = new UserBean();
        user.setNome(nome);
        user.setCognome(cognome);
        user.setEmail(email);
        user.setPassword(Security.toHash(password));
        user.setTelefono(telefono);
        user.setIndirizzoSpedizione(indirizzoSpedizione);
        user.setCittaSpedizione(cittaSpedizione);
        user.setCapSpedizione(capSpedizione);
        user.setProvinciaSpedizione(provinciaSpedizione);
        user.setIsAdmin(false);
        
        try {
            if (!userDAO.isRegistrato(email)) {
                userDAO.doSave(user);
                response.sendRedirect("catalogo.jsp");
            } else {
                response.sendRedirect("register.jsp?error=email_exists");
            }
        } catch (SQLException e) {
            response.sendRedirect("register.jsp?error=db_error");
        }
    }
}