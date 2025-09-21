<%@ page import="model.UserBean, model.CarrelloBean" %>
<%
UserBean currentUser = (UserBean) session.getAttribute("currentUser");
CarrelloBean carrello = (CarrelloBean) session.getAttribute("carrello");
int quantitaCarrello = (carrello != null) ? carrello.getQuantitaTotale() : 0;
%>

<header class="navbar">
  <div class="container">
    <a href="index.jsp" class="nav-brand">Apicoltura Corona</a>
    <nav>
      <a href="index.jsp">Home</a>
      <a href="catalogo.jsp">Catalogo</a>
      <a href="carrello.jsp">
        <% if (quantitaCarrello == 0) { %>
          Carrello
        <% } else { %>
          Carrello (<span id="carrelloCounter"><%= quantitaCarrello %></span>)
        <% } %>
      </a>

      <% if (currentUser == null) { %>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Registrati</a>
      <% } else { %>
        <a href="storico-ordini.jsp">I Miei Ordini</a>
        <a href="LogoutServlet">Logout</a>
      <% } %>
    </nav>
  </div>
</header>
