package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        String ajaxRequest = request.getHeader("x-requested-with");
        boolean isAjax = (ajaxRequest != null);
        
        if (isAjax) {
            response.setContentType("application/json");
        }
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            aggiungiProdotto(request, response, isAjax);
        } else if ("remove".equals(action)) {
            rimuoviProdotto(request, response, isAjax);
        } else if ("clear".equals(action)) {
            svuotaCarrello(request, response, isAjax);
        } else if ("decrease".equals(action)) {
            decrementaProdotto(request, response, isAjax);
        } else if ("count".equals(action)) {
            getCount(request, response, isAjax);
        }
    }

    private void aggiungiProdotto(HttpServletRequest request, HttpServletResponse response, boolean isAjax) 
            throws ServletException, IOException {
        
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        CarrelloBean carrello = getCarrello(request);
        
        try {
            ProductDAO productDAO = new ProductDAO();
            ProductBean prodotto = productDAO.doRetrieveByKey(String.valueOf(idProdotto));
            
            if (prodotto != null) {
                boolean trovato = false;
                for (ProductBean p : carrello.getProdotti()) {
                    if (p.getId() == idProdotto) {
                        p.setQuantitaDisponibile(p.getQuantitaDisponibile() + 1);
                        trovato = true;
                        break;
                    }
                }
                
                if (!trovato) {
                    ProductBean prodottoCarrello = new ProductBean();
                    prodottoCarrello.setId(prodotto.getId());
                    prodottoCarrello.setNome(prodotto.getNome());
                    prodottoCarrello.setDescrizione(prodotto.getDescrizione());
                    prodottoCarrello.setPrezzo(prodotto.getPrezzo());
                    prodottoCarrello.setIva(prodotto.getIva());
                    prodottoCarrello.setQuantitaDisponibile(1);
                    prodottoCarrello.setImmagineURL(prodotto.getImmagineURL());
                    prodottoCarrello.setIdCategoria(prodotto.getIdCategoria());
                    
                    carrello.addProduct(prodottoCarrello);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        handleResponse(request, response, isAjax);
    }
    
    private void decrementaProdotto(HttpServletRequest request, HttpServletResponse response, boolean isAjax) 
            throws ServletException, IOException {
        
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        CarrelloBean carrello = getCarrello(request);
        carrello.decrementaQuantita(idProdotto);
        
        handleResponse(request, response, isAjax);
    }
    
    private void rimuoviProdotto(HttpServletRequest request, HttpServletResponse response, boolean isAjax) 
            throws ServletException, IOException {
        
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
        CarrelloBean carrello = getCarrello(request);
        carrello.rimuovi(idProdotto);
        
        handleResponse(request, response, isAjax);
    }
    
    private void svuotaCarrello(HttpServletRequest request, HttpServletResponse response, boolean isAjax) 
            throws ServletException, IOException {
        
        CarrelloBean carrello = getCarrello(request);
        carrello.svuotaCarrello();
        
        handleResponse(request, response, isAjax);
    }
    
    private void getCount(HttpServletRequest request, HttpServletResponse response, boolean isAjax) 
            throws ServletException, IOException {
        
        CarrelloBean carrello = getCarrello(request);
        int count = carrello.getQuantitaTotale();
        
        if (isAjax) {
            PrintWriter out = response.getWriter();
            String jsonResponse = "{\"status\":\"success\",\"count\":" + count + "}";
            out.print(jsonResponse);
            out.flush();
        }
    }
    
    private void handleResponse(HttpServletRequest request, HttpServletResponse response, boolean isAjax) 
            throws ServletException, IOException {
        
        if (isAjax) {
            CarrelloBean carrello = getCarrello(request);
            int count = carrello.getQuantitaTotale();
            
            PrintWriter out = response.getWriter();
            String jsonResponse = "{\"status\":\"success\",\"count\":" + count + "}";
            out.print(jsonResponse);
            out.flush();
        } else {
            response.sendRedirect("carrello.jsp");
        }
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