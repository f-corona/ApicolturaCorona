<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UserBean, model.OrderBean, model.OrderDAO, model.UserDAO, java.util.Collection, java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%
    DecimalFormat df = new DecimalFormat("0.00");
%>
<%
UserBean adminUser = (UserBean) session.getAttribute("currentUser");
if (adminUser == null || !adminUser.isAdmin()) {
    response.sendRedirect("../login.jsp");
    return;
}

OrderDAO orderDAO = new OrderDAO();
Collection<OrderBean> ordini = orderDAO.doRetrieveAll();
SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
UserDAO userDAO = new UserDAO();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestione Ordini - Apicoltura Corona</title>
<link rel="stylesheet" href="../styles/global.css">
<link rel="stylesheet" href="../styles/admin.css">
<link rel="stylesheet" href="../styles/footer.css">
<link rel="icon" href="../images/apicolturaCoronaLogo.png" type="image/png">
</head>
<body>

<section class="hero">
<h1>Gestione Ordini</h1>
</section>

<div class="container-admin">
    <% if (ordini.isEmpty()) { %>
        <p>Nessun ordine presente</p>
    <% } else { %>
        <% for (OrderBean ordine : ordini) { %>
            <%
                UserBean cliente = userDAO.doRetrieveByKey(String.valueOf(ordine.getIdUtente()));
            %>
            <div class="form-admin">
                <h3>Ordine #<%= ordine.getIdOrdine() %></h3>
                <p><strong>Cliente:</strong> <%= ordine.getIdUtente() %></p>
                <% if (cliente != null) { %>
                    <p><strong>Nome:</strong> <%= cliente.getNome() %> <%= cliente.getCognome() %></p>
                    <p><strong>Email:</strong> <%= cliente.getEmail() %></p>
                <% } %>
                <p><strong>Data:</strong> <%= formatter.format(ordine.getDataOrdine()) %></p>
                <p><strong>Stato:</strong> <%= ordine.getStato() %></p>
                <p><strong>Totale:</strong> €<%= ordine.getTotale() %></p>
            </div>
        <% } %>
    <% } %>
    
    <p><a href="dashboard.jsp">Torna alla Dashboard</a></p>
</div>

<%@ include file="../footer.jsp" %>
</body>
</html>