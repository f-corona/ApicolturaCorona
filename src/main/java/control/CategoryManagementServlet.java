package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import model.CategoryBean;
import model.CategoryDAO;
import model.UserBean;

/**
 * Servlet implementation class CategoryManagmentServlet
 */
@WebServlet("/CategoryManagementServlet")
public class CategoryManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryManagementServlet() {
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
	           CategoryDAO categoryDAO = new CategoryDAO();
	           
	           if ("insert".equals(action)) {
	               insertCategory(request, categoryDAO);
	               response.sendRedirect("admin/categories.jsp?success=inserted");
	               
	           } else if ("update".equals(action)) {
	               updateCategory(request, categoryDAO);
	               response.sendRedirect("admin/categories.jsp?success=updated");
	               
	           } else if ("delete".equals(action)) {
	               deleteCategory(request, categoryDAO);
	               response.sendRedirect("admin/categories.jsp?success=deleted");
	               
	           } else {
	               response.sendRedirect("admin/categories.jsp?error=invalid_action");
	           }
	           
	       } catch (SQLException e) {
	           e.printStackTrace();
	           response.sendRedirect("admin/categories.jsp?error=db_error");
	       } catch (NumberFormatException e) {
	           e.printStackTrace();
	           response.sendRedirect("admin/categories.jsp?error=invalid_data");
	       }
	   }
	   
	   private void insertCategory(HttpServletRequest request, CategoryDAO categoryDAO) throws SQLException {
	       CategoryBean categoria = new CategoryBean();
	       categoria.setNome(request.getParameter("nome"));
	       categoryDAO.doSave(categoria);
	   }
	   
	   private void updateCategory(HttpServletRequest request, CategoryDAO categoryDAO) throws SQLException {
	       String id = request.getParameter("id");
	       CategoryBean categoria = categoryDAO.doRetrieveByKey(id);
	       
	       if (categoria != null) {
	           categoria.setNome(request.getParameter("nome"));
	           categoryDAO.doUpdate(categoria);
	       }
	   }
	   
	   private void deleteCategory(HttpServletRequest request, CategoryDAO categoryDAO) throws SQLException {
	       String id = request.getParameter("id");
	       categoryDAO.doDelete(id);
	   }

}
