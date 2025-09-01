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

@WebServlet("/ProductManagementServlet")
public class ProductManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ProductManagementServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        ProductDAO productDAO = new ProductDAO();
        
        try {
            if ("insert".equals(action)) {
                ProductBean prodotto = new ProductBean();
                prodotto.setNome(request.getParameter("nome"));
                prodotto.setDescrizione(request.getParameter("descrizione"));
                prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                prodotto.setIva(Double.parseDouble(request.getParameter("iva")));
                prodotto.setQuantitaDisponibile(Integer.parseInt(request.getParameter("quantita")));
                prodotto.setImmagineURL(request.getParameter("immagineURL"));
                prodotto.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
                
                productDAO.doSave(prodotto);
                response.sendRedirect("admin/products.jsp");
            } else {
                response.sendRedirect("admin/products.jsp");
            }
        } catch (SQLException e) {
            response.sendRedirect("admin/products.jsp?error=db_error");
        }
    }
}