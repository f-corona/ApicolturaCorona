<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Registrazione</title>
   <link rel="stylesheet" href="styles/register.css">

    <link rel="icon" href="images/apicolturaCoronaLogo.png" type="image/png">
</head>
<body>
    

    <div class="auth-container">
        <h1>Registrazione</h1>
        <form action="RegistrazioneServlet" method="post" class="auth-form">
            <input type="email" name="email" placeholder="Email"><br>
            <input type="password" name="password" placeholder="Password"><br>
            <input type="password" name="confermaPassword" placeholder="Conferma Password"><br>
            <input type="text" name="nome" placeholder="Nome"><br>
            <input type="text" name="cognome" placeholder="Cognome"><br>
            <input type="text" name="telefono" placeholder="Telefono (Opzionale)"><br>
            <input type="text" name="indirizzoSpedizione" placeholder="Indirizzo Spedizione"><br>
            <input type="text" name="cittaSpedizione" placeholder="Città"><br>
            <input type="text" name="capSpedizione" placeholder="CAP"><br>
            <input type="text" name="provinciaSpedizione" placeholder="Provincia (es. SA)"><br>
            <input type="submit" value="Registrati" class="bottone"><br>
        </form>
        <p>Hai già un account? <a href="login.jsp">Accedi</a></p>
    </div>
</body>
</html>
