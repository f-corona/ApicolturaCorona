<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UserBean" %>
<%
    UserBean adminUser = (UserBean) session.getAttribute("currentUser");
    if (adminUser == null || !adminUser.isAdmin()) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard Admin</title>
<link rel="stylesheet" href="../styles/global.css">
<link rel="stylesheet" href="../styles/admin.css">
<link rel="stylesheet" href="../styles/footer.css">
<link rel="icon" href="../images/apicolturaCoronaLogo.png" type="image/png">
</head>
<body>

<section class="hero">
<h1>Dashboard Amministratore</h1>
<p>Benvenuto <%= adminUser.getNome() %></p>
</section>

<div class="div-admin">
    <h2>Gestione Admin</h2>
    <ul>
        <li><a href="categories.jsp">Gestione Categorie</a></li>
        <li><a href="products.jsp">Gestione Prodotti</a></li>
        <li><a href="ordini.jsp">Visualizza Ordini</a></li>
    </ul>
    
    <h2>Navigazione Sito</h2>
    <ul>
        <li><a href="../index.jsp">Home</a></li>
        <li><a href="../catalogo.jsp">Catalogo</a></li>
        <li><a href="../carrello.jsp">Carrello</a></li>
    </ul>
    
    <h2><a href="../LogoutServlet">Logout</a></h2>
</div>

<%@ include file="../footer.jsp" %>
</body>
</html>