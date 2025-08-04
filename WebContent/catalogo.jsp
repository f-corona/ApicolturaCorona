<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ProductBean, model.ProductDAO, model.CategoryBean, model.CategoryDAO, java.util.Collection" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalogo - Apicoltura Corona</title>
    <link rel="stylesheet" href="styles/home.css">
    <link rel="stylesheet" href="styles/navbar.css">
    <link rel="stylesheet" href="styles/catalogo.css">
    <link rel="icon" href="images/apicolturaCoronaLogo.png" type="image/png">

</head>
<body>
   <header class="navbar">
       <div class="container">
           <a href="index.html" class="nav-brand">Apicoltura Corona</a>
           <nav>
               <ul>
                   <li><a href="#chi-siamo">Chi Siamo</a></li>
                   <li><a href="#mieli">I Nostri Mieli</a></li>
                   <li><a href="#contatti">Contatti</a></li>
                   <li><a href="carrello.jsp">Carrello</a></li>
                   <li><a href="LogoutServlet">Logout</a></li>
               </ul>
           </nav>
       </div>
   </header>

   <section class="hero">
       <h1>Il Nostro Catalogo</h1>
       <p>Scopri i nostri prodotti di alta qualità.</p>
   </section>

   <%
       CategoryDAO categoryDAO = new CategoryDAO();
       ProductDAO productDAO = new ProductDAO();
       Collection<CategoryBean> categorie = categoryDAO.doRetrieveAll("Nome");
       
       for (CategoryBean categoria : categorie) {
   %>
       <section class="section">
           <h2><%= categoria.getNome() %></h2>
           
           <%
               Collection<ProductBean> prodotti = productDAO.doRetrieveByCategory(categoria.getId());
               if (prodotti.isEmpty()) {
           %>
               <p>Nessun prodotto disponibile in questa categoria.</p>
           <%
               } else {
                   for (ProductBean prodotto : prodotti) {
           %>
               <div>
                   <% if (prodotto.getImmagineURL() != null && !prodotto.getImmagineURL().isEmpty()) { %>
                       <img src="<%= prodotto.getImmagineURL() %>" 
                            alt="<%= prodotto.getNome() %>" 
                            class="product-image"
                            style="max-width: 200px; margin-bottom: 10px;"
                            onclick="openModal('<%= prodotto.getImmagineURL() %>', '<%= prodotto.getNome() %>')">
                   <% } %>
                   <h3><%= prodotto.getNome() %></h3>
                   <p><%= prodotto.getDescrizione() %></p>
                   <p><strong>€<%= String.format("%.2f", prodotto.getPrezzoConIva()) %></strong></p>
                   
                   <% if (prodotto.getQuantitaDisponibile() > 0) { %>
                       <form action="CarrelloServlet" method="post" style="display:inline;">
                           <input type="hidden" name="action" value="add">
                           <input type="hidden" name="idProdotto" value="<%= prodotto.getId() %>">
                           <input type="submit" value="Aggiungi al carrello">
                       </form>
                   <% } else { %>
                       <p style="color: red;"><strong>Non disponibile</strong></p>
                   <% } %>
               </div>
               <br>
           <%
                   }
               }
           %>
       </section>
   <%
       }
   %>

   <div id="imageModal" class="modal">
       <span class="close" onclick="closeModal()">&times;</span>
       <img id="modalImage">
   </div>

   <footer id="contatti" class="footer">
       <h3>Contattaci</h3>
   </footer>

   <script src="scripts/script.js"></script>
</body>
</html>