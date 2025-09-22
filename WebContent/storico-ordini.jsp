<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UserBean, model.OrderBean, model.OrderDAO, java.util.Collection, java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%
    DecimalFormat df = new DecimalFormat("0.00");
%>
<%
    UserBean user = (UserBean) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    OrderDAO orderDAO = new OrderDAO();
    Collection<OrderBean> ordini = orderDAO.doRetrieveByUser(user.getId());
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>I Miei Ordini</title>
<link rel="stylesheet" href="styles/global.css">
<link rel="stylesheet" href="styles/navbar.css">
<link rel="stylesheet" href="styles/footer.css">
</head>
<body>

<%@ include file="header.jsp" %>

<section class="hero">
<h1>I Miei Ordini</h1>
</section>

<section class="section">
<% if (ordini.isEmpty()) { %>
    <p>Non hai ancora effettuato ordini.</p>
    <a href="catalogo.jsp">Inizia lo shopping</a>
<% } else { %>
    <% for (OrderBean ordine : ordini) { %>
        <div style="border: 1px solid #dddddd; margin: 15px auto; padding: 20px; max-width: 600px; border-radius: 5px;">
            <h3>Ordine #<%= ordine.getIdOrdine() %></h3>
            <p>Data: <%= formatter.format(ordine.getDataOrdine()) %></p>
            <p>Stato: <%= ordine.getStato() %></p>
            <p>Totale: €<%= ordine.getTotale() %></p>
        </div>
    <% } %>
<% } %>

<p><a href="catalogo.jsp">Torna al Catalogo</a></p>
</section>

<%@ include file="footer.jsp" %>

</body>
</html>