package control;

import model.ProductBean;
import model.ProductDAO;
import model.UserBean;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;



/**
 * Servlet implementation class ProductManagementServlet
 */
@WebServlet("/ProductManagementServlet")
public class ProductManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductManagementServlet() {
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
		request.setCharacterEncoding("UTF-8");
	       response.setContentType("text/html; charset=UTF-8");
	       
	       // Controllo accesso admin
	       HttpSession session = request.getSession(false);
	       if (session == null || session.getAttribute("currentUser") == null) {
	           response.sendRedirect("login.jsp");
	           return;
	       }
	       
	       UserBean user = (UserBean) session.getAttribute("currentUser");
	       if (!user.isAdmin()) {
	           response.sendRedirect("catalogo.jsp");
	           return;
	       }
	       
	       String action = request.getParameter("action");
	       
	       try {
	           ProductDAO productDAO = new ProductDAO();
	           
	           if ("insert".equals(action)) {
	               insertProduct(request, productDAO);
	               response.sendRedirect("admin/products.jsp?success=inserted");
	               
	           } else if ("update".equals(action)) {
	               updateProduct(request, productDAO);
	               response.sendRedirect("admin/products.jsp?success=updated");
	               
	           } else if ("delete".equals(action)) {
	               deleteProduct(request, productDAO);
	               response.sendRedirect("admin/products.jsp?success=deleted");
	               
	           } else {
	               response.sendRedirect("admin/products.jsp?error=invalid_action");
	           }
	           
	       } catch (SQLException e) {
	           e.printStackTrace();
	           response.sendRedirect("admin/products.jsp?error=db_error");
	       } catch (NumberFormatException e) {
	           e.printStackTrace();
	           response.sendRedirect("admin/products.jsp?error=invalid_data");
	       }
	   }
	   
	   private void insertProduct(HttpServletRequest request, ProductDAO productDAO) throws SQLException {
	       ProductBean prodotto = new ProductBean();
	       
	       prodotto.setNome(request.getParameter("nome"));
	       prodotto.setDescrizione(request.getParameter("descrizione"));
	       prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
	       prodotto.setIva(Double.parseDouble(request.getParameter("iva")));
	       prodotto.setQuantitaDisponibile(Integer.parseInt(request.getParameter("quantita")));
	       prodotto.setImmagineURL(request.getParameter("immagineURL"));
	       prodotto.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
	       
	       productDAO.doSave(prodotto);
	   }
	   
	   private void updateProduct(HttpServletRequest request, ProductDAO productDAO) throws SQLException {
	       String id = request.getParameter("id");
	       ProductBean prodotto = productDAO.doRetrieveByKey(id);
	       
	       if (prodotto != null) {
	           prodotto.setNome(request.getParameter("nome"));
	           prodotto.setDescrizione(request.getParameter("descrizione"));
	           prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
	           prodotto.setIva(Double.parseDouble(request.getParameter("iva")));
	           prodotto.setQuantitaDisponibile(Integer.parseInt(request.getParameter("quantita")));
	           prodotto.setImmagineURL(request.getParameter("immagineURL"));
	           prodotto.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
	           
	           productDAO.doUpdate(prodotto);
	       }
	   }
	   
	   private void deleteProduct(HttpServletRequest request, ProductDAO productDAO) throws SQLException {
	       String id = request.getParameter("id");
	       productDAO.doDelete(id);
	   }
	}

