<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean" %>
<%
    UserBean user = (UserBean) session.getAttribute("currentUser");
    if (user == null || !user.isAdmin()) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
<h1>Dashboard</h1>
    <p>Benvenuto <%= user.getNome() %></p>
    
    <ul>
        <li><a href="categories.jsp">Categorie</a></li>
        <li><a href="products.jsp">Prodotti</a></li>

    </ul>
</body>
</html>



