<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<!--  login test
franco@unisa.it
pass123?-->
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="styles/login.css">

	<link rel="icon" href="images/apicolturaCoronaLogo.png" type="image/png">
</head>
<body>
 
    <div class="auth-container">
        <h1>Login</h1>
        <form action="LoginServlet" method="post" class="auth-form">
            <input type="text" name="email" placeholder="Email">
            <input type="password" name="password" placeholder="Password">
            <input type="submit" value="Login" class="bottone">
        </form>
        <p>Non sei registrato? <a href="register.jsp">Registrati</a></p>
    </div>
</body>
</html>