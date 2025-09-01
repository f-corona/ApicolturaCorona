<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.CarrelloBean" %>
<%
String error = request.getParameter("error");
String errorMessage = "";
if (error != null) {
    switch(error) {
        case "invalid_credentials":
            errorMessage = "Email o password non corretti";
            break;
        case "campi_vuoti":
            errorMessage = "Inserisci email e password";
            break;
        case "db_error":
            errorMessage = "Errore del sistema. Riprova più tardi";
            break;
        default:
            errorMessage = "Errore durante il login";
    }
}
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Apicoltura Corona</title>
    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="styles/navbar.css">
    <link rel="stylesheet" href="styles/login.css">
    <link rel="stylesheet" href="styles/footer.css">
    <link rel="icon" href="images/apicolturaCoronaLogo.png" type="image/png">
    <script src="scripts/validazione-login.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container-auth">
    <h1>Login</h1>
    
    <form id="form-login" action="LoginServlet" method="post" class="form-auth">
        <input type="text" id="email" name="email" placeholder="Email" required 
               value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>">
        <div id="email-error" class="errore"></div>
        
        <input type="password" id="password" name="password" placeholder="Password" required>
        <div id="password-error" class="errore"></div>
        
        <% if (!errorMessage.isEmpty()) { %>
            <div class="errore-server"><%= errorMessage %></div>
        <% } %>
        
        <input type="submit" value="Login" class="bottone">
    </form>
    <p>Non sei registrato? <a href="register.jsp">Registrati</a></p>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>