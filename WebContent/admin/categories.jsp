<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Categorie</title>
</head>
<body>
<h1>Inserisci Categoria</h1>

<form action="<%= request.getContextPath() %>/CategoryManagementServlet" method="post">
    <input type="hidden" name="action" value="insert">
    <input type="text" name="nome" placeholder="Nome Categoria" required><br><br>
    <input type="submit" value="Inserisci">
</form>

<br>
<a href="dashboard.jsp">Torna al Dashboard</a>

</body>
</html>