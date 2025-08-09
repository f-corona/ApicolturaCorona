package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import model.CarrelloBean;
import model.ProductBean;
import model.ProductDAO;

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("carrello.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            aggiungiProdotto(request, response);
        } else if ("remove".equals(action)) {
            rimuoviProdotto(request, response);
        } else if ("clear".equals(action)) {
            svuotaCarrello(request, response);
        } else if ("decrease".equals(action)) {
            decrementaProdotto(request, response);
        }
    }

    private void aggiungiProdotto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        
        CarrelloBean carrello = getCarrello(request);
        
        try {
            ProductDAO productDAO = new ProductDAO();
            ProductBean prodotto = productDAO.doRetrieveByKey(String.valueOf(idProdotto));
            
            if (prodotto != null) {
                ProductBean prodottoCarrello = new ProductBean();
                prodottoCarrello.setId(prodotto.getId());
                prodottoCarrello.setNome(prodotto.getNome());
                prodottoCarrello.setDescrizione(prodotto.getDescrizione());
                prodottoCarrello.setPrezzo(prodotto.getPrezzo());
                prodottoCarrello.setIva(prodotto.getIva());
                prodottoCarrello.setQuantitaDisponibile(1);
                prodottoCarrello.setImmagineURL(prodotto.getImmagineURL());
                prodottoCarrello.setIdCategoria(prodotto.getIdCategoria());
                
                if (!carrello.isPresente(prodottoCarrello)) {
                    carrello.addProduct(prodottoCarrello);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //controllo per evitare di essere rimandati al catalogo quando sto nel carrello
        String referer = request.getHeader("Referer");
        if (referer != null && referer.contains("carrello.jsp")) {
            response.sendRedirect("carrello.jsp");
        } else {
            response.sendRedirect("catalogo.jsp");
        }
    }
    
    private void decrementaProdotto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        CarrelloBean carrello = getCarrello(request);
        carrello.decrementaQuantita(idProdotto);
        response.sendRedirect("carrello.jsp");
    }
    
    private void rimuoviProdotto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        CarrelloBean carrello = getCarrello(request);
        carrello.rimuovi(idProdotto);
        response.sendRedirect("carrello.jsp");
    }
    
    private void svuotaCarrello(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        CarrelloBean carrello = getCarrello(request);
        carrello.svuotaCarrello();
        response.sendRedirect("carrello.jsp");
    }
    
    private CarrelloBean getCarrello(HttpServletRequest request) {
        CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("carrello");
        if (carrello == null) {
            carrello = new CarrelloBean();
            request.getSession().setAttribute("carrello", carrello);
        }
        return carrello;
    }
}