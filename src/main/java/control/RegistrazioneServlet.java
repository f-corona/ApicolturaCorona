package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import model.UserDAO;
import model.UserBean;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// imposta la codifica dei caratteri per gestire correttamente i caratteri speciali
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// prendo i parametri necessari alla registrazione dal form
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
        boolean isAdmin = false; // registrato di default non è admin
	    
	    UserDAO udao = new UserDAO();
	    
	    if (!password.equals(confermaPassword)) {
             response.sendRedirect("register.jsp?error=password_mismatch");
            return;
        }
	    
	    // creo userbean con i setter
	    
	    UserBean ubean = new UserBean();
        ubean.setNome(nome);
        ubean.setCognome(cognome);
        ubean.setEmail(email);
        ubean.setPassword(password);
        
        ubean.setTelefono(telefono);
        ubean.setIndirizzoSpedizione(indirizzoSpedizione);
        ubean.setCittaSpedizione(cittaSpedizione);
        ubean.setCapSpedizione(capSpedizione);
        ubean.setProvinciaSpedizione(provinciaSpedizione);
                
        ubean.setIsAdmin(isAdmin);
	    
	    try {
	    	// Se la mail non è già presente nel db, la salva
			if(!udao.isRegistrato(email)) {
				udao.doSave(ubean);
				response.sendRedirect("catalogo.jsp"); // Reindirizza al catalogo o a una pagina di login
			}else {
				// Invia alla registrazione settando il parametro ar (already registered)
				response.sendRedirect("register.jsp?ar=y");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
            // In caso di errore SQL generico, reindirizza al form con un errore DB
			response.sendRedirect("register.jsp?error=db_error");
		}
	}

}