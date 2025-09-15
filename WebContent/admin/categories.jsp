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
<title>Gestione Categorie - Apicoltura Corona</title>
<link rel="stylesheet" href="../styles/global.css">
<link rel="stylesheet" href="../styles/admin.css">
<link rel="stylesheet" href="../styles/footer.css">
<link rel="icon" href="../images/apicolturaCoronaLogo.png" type="image/png">
</head>
<body>

<section class="hero">
<h1>Gestione Categorie</h1>
</section>

<div class="container-admin">
    <form action="<%= request.getContextPath() %>/CategoryManagementServlet" method="post" class="form-admin">
        <input type="hidden" name="action" value="insert">
        
        <div class="gruppo">
            <label>Nome Categoria:</label>
            <input type="text" name="nome" required>
        </div>
        
        <input type="submit" value="Inserisci Categoria" class="bottone">
    </form>
    
    <p><a href="dashboard.jsp">Torna alla Dashboard</a></p>
</div>

<footer class="footer">
<h3>Contattaci</h3>
</footer>

</body>
</html>