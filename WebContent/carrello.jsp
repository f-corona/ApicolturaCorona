<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.CarrelloBean, model.ProductBean, java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello - Apicoltura Corona</title>
    <link rel="stylesheet" href="styles/home.css">
    <link rel="stylesheet" href="styles/navbar.css">
    <link rel="icon" href="images/apicolturaCoronaLogo.png" type="image/png">
</head>
<body>
    <%
        HttpSession sessione = request.getSession(true);
        CarrelloBean carrello = (CarrelloBean) sessione.getAttribute("carrello");
        if (carrello == null) {
            carrello = new CarrelloBean();
            sessione.setAttribute("carrello", carrello);
        }
    %>
    
    <header class="navbar">
        <div class="container">
            <a href="index.html" class="nav-brand">Apicoltura Corona</a>
            <nav>
                <ul>
                    <li><a href="catalogo.jsp">Catalogo</a></li>
                    <li><a href="carrello.jsp">Carrello</a></li>
                    <li><a href="LogoutServlet">Logout</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <section class="hero">
        <h1>Il Tuo Carrello</h1>
        <p>Prodotti selezionati per l'acquisto.</p>
    </section>

    <section class="section">
        <% if (carrello.isEmpty()) { %>
            <p>Il carrello è vuoto.</p>
            <a href="catalogo.jsp">Continua lo shopping</a>
        <% } else { %>
            
            <h2>Prodotti</h2>
            
            <% 
                ArrayList<ProductBean> prodotti = carrello.getProdotti();
                for (ProductBean prodotto : prodotti) {
            %>
                <div style="border: 1px solid #ccc; margin: 10px; padding: 15px;">
                    <% if (prodotto.getImmagineURL() != null && !prodotto.getImmagineURL().isEmpty()) { %>
                        <img src="<%= prodotto.getImmagineURL() %>" alt="<%= prodotto.getNome() %>" style="max-width: 80px; margin-right: 15px;">
                    <% } %>
                    
                    <h3><%= prodotto.getNome() %></h3>
                    <p><%= prodotto.getDescrizione() %></p>
                    <p><strong>€<%= String.format("%.2f", prodotto.getPrezzoConIva()) %></strong></p>
                    
                    <div>
                        <form action="CarrelloServlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="decrease">
                            <input type="hidden" name="idProdotto" value="<%= prodotto.getId() %>">
                            <input type="submit" value="-">
                        </form>
                        
                        <span>Quantità: <%= prodotto.getQuantitaDisponibile() %></span>
                        
                        <form action="CarrelloServlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="idProdotto" value="<%= prodotto.getId() %>">
                            <input type="submit" value="+">
                        </form>
                        
                        <form action="CarrelloServlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="idProdotto" value="<%= prodotto.getId() %>">
                            <input type="submit" value="Rimuovi" style="background-color: red; color: white;">
                        </form>
                    </div>
                </div>
            <% } %>
            
            <div style="margin-top: 20px; padding: 15px; border: 2px solid green;">
                <h3>Riepilogo</h3>
                <p><strong>Totale: €<%= String.format("%.2f", carrello.sommaPrezzo()) %></strong></p>
                
                <form action="CarrelloServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="clear">
                    <input type="submit" value="Svuota Carrello" style="background-color: orange; color: white; margin-right: 10px;">
                </form>
                
                <form action="CheckoutServlet" method="post" style="display:inline;">
                    <input type="submit" value="Procedi all'Ordine" style="background-color: green; color: white;">
                </form>
            </div>
            
        <% } %>
    </section>

    <footer class="footer">
        <h3>Contattaci</h3>
    </footer>

</body>
</html>