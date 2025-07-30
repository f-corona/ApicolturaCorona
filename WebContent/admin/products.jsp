<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UserBean, model.CategoryBean, model.CategoryDAO, java.util.Collection" %>
<%
    UserBean user = (UserBean) session.getAttribute("currentUser");
    if (user == null || !user.isAdmin()) {
        response.sendRedirect("../login.jsp");
        return;
    }
    
    CategoryDAO categoryDAO = new CategoryDAO();
    Collection<CategoryBean> categorie = categoryDAO.doRetrieveAll("Nome");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prodotti</title>
</head>
<body>
<h1>Inserisci Prodotto</h1>

<form action="../ProductManagementServlet" method="post">
    <input type="hidden" name="action" value="insert">
    
    <input type="text" name="nome" placeholder="Nome Prodotto" required><br><br>
    <textarea name="descrizione" placeholder="Descrizione" required></textarea><br><br>
    <input type="number" step="0.01" name="prezzo" placeholder="Prezzo" required><br><br>
    <input type="number" step="0.01" name="iva" placeholder="IVA %" required><br><br>
    <input type="number" name="quantita" placeholder="Quantità" required><br><br>
    <input type="text" name="immagineURL" placeholder="URL Immagine"><br><br>
    
    <select name="categoria" required>
        <option value="">Seleziona Categoria</option>
        <% for (CategoryBean categoria : categorie) { %>
            <option value="<%= categoria.getId() %>"><%= categoria.getNome() %></option>
        <% } %>
    </select><br><br>
    
    <input type="submit" value="Inserisci">
</form>

<br>
<a href="dashboard.jsp">Torna al Dashboard</a>

</body>
</html>