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
<ul>
<li><a href="index.jsp">Home</a></li>
<li><a href="catalogo.jsp">Catalogo</a></li>
<li><a href="carrello.jsp">
<% if (quantitaCarrello == 0) { %>
Carrello
<% } else { %>
Carrello (<span id="carrelloCounter"><%= quantitaCarrello %></span>)
<% } %>
</a></li>

<% if (currentUser == null) { %>
<li><a href="login.jsp">Login</a></li>
<li><a href="register.jsp">Registrati</a></li>
<% } else { %>
<li><a href="storico-ordini.jsp">I Miei Ordini</a></li>
<li><a href="LogoutServlet">Logout</a></li>
<% } %>

</ul>
</nav>
</div>
</header>