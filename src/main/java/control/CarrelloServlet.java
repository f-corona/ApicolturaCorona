package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;

import model.CarrelloBean;
import model.ProductBean;
import model.ProductDAO;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("carrello");
        if (carrello == null) {
            carrello = new CarrelloBean();
            request.getSession().setAttribute("carrello", carrello);
        }

        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/carrello.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String action = request.getParameter("action");
        
        if (action != null) {
            if (action.equalsIgnoreCase("add")) {
                aggiungiProdotto(request, response);
            } else if (action.equalsIgnoreCase("remove")) {
                rimuoviProdotto(request, response);
            } else if (action.equalsIgnoreCase("clear")) {
                svuotaCarrello(request, response);
            }
        }
    }
    
    private void aggiungiProdotto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
            
            ProductDAO productDAO = new ProductDAO();
            ProductBean prodotto = productDAO.doRetrieveByKey(String.valueOf(idProdotto));
            
            if (prodotto != null && prodotto.getQuantitaDisponibile() > 0) {
                CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("carrello");
                if (carrello == null) {
                    carrello = new CarrelloBean();
                    request.getSession().setAttribute("carrello", carrello);
                }
                
                // Imposta quantità a 1 per il carrello
                ProductBean prodottoCarrello = new ProductBean();
                prodottoCarrello.setId(prodotto.getId());
                prodottoCarrello.setNome(prodotto.getNome());
                prodottoCarrello.setDescrizione(prodotto.getDescrizione());
                prodottoCarrello.setPrezzo(prodotto.getPrezzo());
                prodottoCarrello.setIva(prodotto.getIva());
                prodottoCarrello.setQuantitaDisponibile(1); // Quantità nel carrello
                prodottoCarrello.setImmagineURL(prodotto.getImmagineURL());
                prodottoCarrello.setIdCategoria(prodotto.getIdCategoria());
                
                if (!carrello.isPresente(prodottoCarrello)) {
                    carrello.addProduct(prodottoCarrello);
                }
                
                request.getSession().setAttribute("carrello", carrello);
            }
            
            response.sendRedirect("catalogo.jsp");
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("catalogo.jsp?error=db_error");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("catalogo.jsp?error=invalid_id");
        }
    }
    
    private void rimuoviProdotto(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
            
            CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("carrello");
            if (carrello != null) {
                carrello.rimuovi(idProdotto);
                request.getSession().setAttribute("carrello", carrello);
            }
            
            response.sendRedirect("carrello.jsp");
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("carrello.jsp?error=invalid_id");
        }
    }
    
    private void svuotaCarrello(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("carrello");
        if (carrello != null) {
            carrello.svuotaCarrello();
            request.getSession().setAttribute("carrello", carrello);
        }
        
        response.sendRedirect("carrello.jsp");
    }
}