<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ProductBean, model.ProductDAO, java.util.Collection" %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Catalogo - Apicoltura Corona</title>
<link rel="stylesheet" href="styles/global.css">
<link rel="stylesheet" href="styles/navbar.css">
<link rel="stylesheet" href="styles/catalogo.css">
<link rel="stylesheet" href="styles/footer.css">
<link rel="icon" href="images/apicolturaCoronaLogo.png" type="image/png">
<script type="text/javascript" src="scripts/carrello-ajax.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>

<section class="hero">
  <h1>Il Nostro Catalogo</h1>
  <p>Scopri i nostri prodotti di alta qualità.</p>
</section>

<%
ProductDAO productDAO = new ProductDAO();
%>

<section class="section">
  <h2>Prodotti dell'Alveare</h2>
  <div class="griglia">
  <%
  Collection<ProductBean> prodottiAlveare = productDAO.doRetrieveByCategory(1);
  for (ProductBean prodotto : prodottiAlveare) {
  %>
    <div class="prodotto">
      <% if (prodotto.getImmagineURL() != null && !prodotto.getImmagineURL().isEmpty()) { %>
        <img src="<%= prodotto.getImmagineURL() %>" alt="<%= prodotto.getNome() %>">
      <% } %>
      <h3><%= prodotto.getNome() %></h3>
      <h3>€<%= prodotto.getPrezzoConIva() %></h3>
      <p><%= prodotto.getDescrizione() %></p>

      <% if (prodotto.getQuantitaDisponibile() > 0) { %>
        <button type="button" onclick="aggiungiAlCarrello(<%= prodotto.getId() %>)">Aggiungi al carrello</button>
      <% } else { %>
        <p style="color: red;"><strong>Non disponibile</strong></p>
      <% } %>
    </div>
  <% } %>
  </div>
</section>

<section class="section">
  <h2>Materiale Apistico</h2>
  <div class="griglia">
  <%
  Collection<ProductBean> prodottiMateriale = productDAO.doRetrieveByCategory(2);
  for (ProductBean prodotto : prodottiMateriale) {
  %>
    <div class="prodotto">
      <% if (prodotto.getImmagineURL() != null && !prodotto.getImmagineURL().isEmpty()) { %>
        <img src="<%= prodotto.getImmagineURL() %>" alt="<%= prodotto.getNome() %>">
      <% } %>
      <h3><%= prodotto.getNome() %></h3>
      <h3>€<%= prodotto.getPrezzoConIva() %></h3>
      <p><%= prodotto.getDescrizione() %></p>

      <% if (prodotto.getQuantitaDisponibile() > 0) { %>
        <button type="button" onclick="aggiungiAlCarrello(<%= prodotto.getId() %>)">Aggiungi al carrello</button>
      <% } else { %>
        <p style="color: red;"><strong>Non disponibile</strong></p>
      <% } %>
    </div>
  <% } %>
  </div>
</section>

<%@ include file="footer.jsp" %>

</body>
</html>