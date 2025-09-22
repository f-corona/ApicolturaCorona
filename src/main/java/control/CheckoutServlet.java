package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


import model.CarrelloBean;
import model.OrderBean;
import model.OrderDAO;
import model.UserBean;
import model.ProductDAO;
import model.ProductBean;


@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("currentUser");
        CarrelloBean carrello = (CarrelloBean) session.getAttribute("carrello");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect("carrello.jsp");
            return;
        }

        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();

        OrderBean ordine = new OrderBean();
        ordine.setIdUtente(user.getId());
        ordine.setDataOrdine(new java.sql.Timestamp(System.currentTimeMillis()));
        ordine.setStato("Confermato");
        ordine.setTotale(carrello.sommaPrezzo());
        ordine.setProdotti(carrello.getProdotti());

        try {
        	 for (ProductBean p : carrello.getProdotti()) {
                 ProductBean prodottoDB = productDAO.doRetrieveByKey(String.valueOf(p.getId()));
                 if (p.getQuantitaDisponibile() > prodottoDB.getQuantitaDisponibile()) {
                	    response.sendRedirect("carrello.jsp?error=troppi_pezzi&nome=" + prodottoDB.getNome());
                	    return;
                	}
             }
        	 
        	int idOrdine = orderDAO.doSave(ordine);
        	session.setAttribute("ultimoOrdineId", idOrdine);

        	
        	// Aggiorna la quantità disponibile dei prodotti
            for (ProductBean p : carrello.getProdotti()) {
                ProductBean prodottoDB = productDAO.doRetrieveByKey(String.valueOf(p.getId()));
                if (prodottoDB != null) {
                    int nuovaQuantita = prodottoDB.getQuantitaDisponibile() - p.getQuantitaDisponibile();
                    prodottoDB.setQuantitaDisponibile(Math.max(nuovaQuantita, 0));
                    productDAO.doUpdate(prodottoDB);
                }
            }

            carrello.svuotaCarrello();
            response.sendRedirect("ordine-confermato.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("carrello.jsp?error=errore_ordine");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
