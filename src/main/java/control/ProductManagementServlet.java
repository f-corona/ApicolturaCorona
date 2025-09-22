package control;

import model.ProductBean;
import model.ProductDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.math.BigDecimal;

@WebServlet("/ProductManagementServlet")
public class ProductManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ProductManagementServlet() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {

        String action = request.getParameter("action");
        ProductDAO productDAO = new ProductDAO();
        
        try {
            if ("insert".equals(action)) {
                ProductBean prodotto = leggiProdottoDaRequest(request);
                productDAO.doSave(prodotto);

            } else if ("update".equals(action)) {
                ProductBean prodotto = leggiProdottoDaRequest(request);
                prodotto.setId(Integer.parseInt(request.getParameter("id")));
                productDAO.doUpdate(prodotto);

            } else if ("delete".equals(action)) {
                String id = request.getParameter("id");
                productDAO.doDelete(id);
            }

            response.sendRedirect("admin/products.jsp");

        } catch (SQLException e) {
            response.sendRedirect("admin/products.jsp?error=db_error");
        }
    }

    private ProductBean leggiProdottoDaRequest(HttpServletRequest request) {
        ProductBean prodotto = new ProductBean();

        prodotto.setNome(request.getParameter("nome"));
        prodotto.setDescrizione(request.getParameter("descrizione"));
        prodotto.setPrezzo(new BigDecimal(request.getParameter("prezzo")));
        prodotto.setIva(new BigDecimal(request.getParameter("iva"))); 
        prodotto.setQuantitaDisponibile(Integer.parseInt(request.getParameter("quantita")));
        prodotto.setImmagineURL(request.getParameter("immagineURL"));
        prodotto.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
        String canc = request.getParameter("cancellato");
        if (canc != null && canc.equals("1")) {
            prodotto.setCancellato(true);
        } else {
            prodotto.setCancellato(false);
        }

        return prodotto;
    }



@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    doPost(request, response);
}

}


