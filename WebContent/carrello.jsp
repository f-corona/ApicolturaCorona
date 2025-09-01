<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ProductBean, java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Carrello - Apicoltura Corona</title>
<link rel="stylesheet" href="styles/global.css">
<link rel="stylesheet" href="styles/navbar.css">
<link rel="stylesheet" href="styles/carrello.css">
<link rel="stylesheet" href="styles/footer.css">
<link rel="icon" href="images/apicolturaCoronaLogo.png" type="image/png">
<script type="text/javascript" src="scripts/carrello-ajax.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>

<section class="hero">
<h1>Il Tuo Carrello</h1>
<p>Prodotti selezionati per l'acquisto.</p>
</section>

<section class="section">
<% if (carrello.isEmpty()) { %>
    <div class="vuoto">
        <p>Il carrello è vuoto.</p>
        <a href="catalogo.jsp">Continua lo shopping</a>
    </div>
<% } else { %>

    <h2>Prodotti</h2>

    <%
    ArrayList<ProductBean> prodotti = carrello.getProdotti();
    for (ProductBean prodotto : prodotti) {
    %>
    <div class="prodotto-cart">
        <% if (prodotto.getImmagineURL() != null && !prodotto.getImmagineURL().isEmpty()) { %>
        <img src="<%= prodotto.getImmagineURL() %>" alt="<%= prodotto.getNome() %>">
        <% } %>

        <h3><%= prodotto.getNome() %></h3>
        <h3>€<%= prodotto.getPrezzoConIva() %></h3>
        <p><%= prodotto.getDescrizione() %></p>

        <div class="controlli">
            <button type="button" class="btn-small" onclick="decrementaQuantita(<%= prodotto.getId() %>)">-</button>
            <span>Quantità: <%= prodotto.getQuantitaDisponibile() %></span>
            <button type="button" class="btn-small" onclick="aggiungiAlCarrello(<%= prodotto.getId() %>)">+</button>
            <button type="button" class="btn-remove" onclick="rimuoviDalCarrello(<%= prodotto.getId() %>)">Rimuovi</button>
        </div>
    </div>
    <% } %>

    <div class="totale">
        <h3>Riepilogo</h3>
        <p><strong>Totale: €<%= carrello.sommaPrezzo() %></strong></p>

        <button type="button" class="btn-clear" onclick="svuotaCarrello()">Svuota Carrello</button>

        <form action="CheckoutServlet" method="post" style="display:inline;">
            <input type="submit" value="Procedi all'Ordine" class="btn-buy">
        </form>
    </div>

<% } %>
</section>

<%@ include file="footer.jsp" %>

</body>
</html>