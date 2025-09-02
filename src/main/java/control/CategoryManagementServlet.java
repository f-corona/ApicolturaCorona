package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import model.CategoryBean;
import model.CategoryDAO;
import model.UserBean;

@WebServlet("/CategoryManagementServlet")
public class CategoryManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	HttpSession session = request.getSession(false);
    	UserBean currentUser = (session != null) ? (UserBean) session.getAttribute("currentUser") : null;

    	if (currentUser == null || !currentUser.isAdmin()) {
    	    response.sendRedirect("login.jsp");
    	    return;
    	}


        
        UserBean user = (UserBean) session.getAttribute("currentUser");
        if (!user.isAdmin()) {
            response.sendRedirect("catalogo.jsp");
            return;
        }
        
        String action = request.getParameter("action");
        CategoryDAO categoryDAO = new CategoryDAO();
        
        try {
            if ("insert".equals(action)) {
                CategoryBean categoria = new CategoryBean();
                categoria.setNome(request.getParameter("nome"));
                categoryDAO.doSave(categoria);
                response.sendRedirect("admin/categories.jsp");
                
            } else if ("delete".equals(action)) {
                String nome = request.getParameter("nome");
                categoryDAO.doDelete(nome);
                response.sendRedirect("admin/categories.jsp");
                
            } else {
                response.sendRedirect("admin/categories.jsp");
            }
        } catch (SQLException e) {
            response.sendRedirect("admin/categories.jsp?error=db_error");
        }
    }
}