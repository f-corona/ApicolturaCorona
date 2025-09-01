<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.CarrelloBean" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione - Apicoltura Corona</title>
    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="styles/navbar.css">
    <link rel="stylesheet" href="styles/register.css">
    <link rel="stylesheet" href="styles/footer.css">
    <link rel="icon" href="images/apicolturaCoronaLogo.png" type="image/png">
    <script src="scripts/validazione-registrazione.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container-auth">
    <h1>Registrazione</h1>
    <form id="form-registrazione" action="RegistrazioneServlet" method="post" class="form-auth">
        <input type="email" id="email" name="email" placeholder="Email" required>
        <div id="email-error" class="errore"></div>
        
        <input type="password" id="password" name="password" placeholder="Password" required>
        <div id="password-error" class="errore"></div>
        
        <input type="password" id="confermaPassword" name="confermaPassword" placeholder="Conferma Password" required>
        <div id="confermaPassword-error" class="errore"></div>
        
        <input type="text" id="nome" name="nome" placeholder="Nome" required>
        <div id="nome-error" class="errore"></div>
        
        <input type="text" id="cognome" name="cognome" placeholder="Cognome" required>
        <div id="cognome-error" class="errore"></div>
        
        <input type="text" id="telefono" name="telefono" placeholder="Telefono (Opzionale)">
        <div id="telefono-error" class="errore"></div>
        
        <input type="text" id="indirizzoSpedizione" name="indirizzoSpedizione" placeholder="Indirizzo Spedizione" required>
        <div id="indirizzoSpedizione-error" class="errore"></div>
        
        <input type="text" id="cittaSpedizione" name="cittaSpedizione" placeholder="Città" required>
        <div id="cittaSpedizione-error" class="errore"></div>
        
        <input type="text" id="capSpedizione" name="capSpedizione" placeholder="CAP" required>
        <div id="capSpedizione-error" class="errore"></div>
        
        <input type="text" id="provinciaSpedizione" name="provinciaSpedizione" placeholder="Provincia (es. SA)" required>
        <div id="provinciaSpedizione-error" class="errore"></div>
        
        <input type="submit" value="Registrati" class="bottone">
    </form>
    <p>Hai già un account? <a href="login.jsp">Accedi</a></p>
</div>

<%@ include file="footer.jsp" %>

<script>
document.getElementById("email").addEventListener("change", function() {
    mostraErrore("email-error", validaEmail(this.value.trim()));
});

document.getElementById("password").addEventListener("change", function() {
    mostraErrore("password-error", validaPassword(this.value));
});

document.getElementById("confermaPassword").addEventListener("change", function() {
    const password = document.getElementById("password").value;
    mostraErrore("confermaPassword-error", validaConfermaPassword(password, this.value));
});

document.getElementById("nome").addEventListener("change", function() {
    mostraErrore("nome-error", validaNome(this.value.trim(), "Nome"));
});

document.getElementById("cognome").addEventListener("change", function() {
    mostraErrore("cognome-error", validaNome(this.value.trim(), "Cognome"));
});

document.getElementById("telefono").addEventListener("change", function() {
    mostraErrore("telefono-error", validaTelefono(this.value.trim()));
});

document.getElementById("indirizzoSpedizione").addEventListener("change", function() {
    mostraErrore("indirizzoSpedizione-error", validaIndirizzo(this.value.trim()));
});

document.getElementById("cittaSpedizione").addEventListener("change", function() {
    mostraErrore("cittaSpedizione-error", validaCitta(this.value.trim()));
});

document.getElementById("capSpedizione").addEventListener("change", function() {
    mostraErrore("capSpedizione-error", validaCap(this.value.trim()));
});

document.getElementById("provinciaSpedizione").addEventListener("change", function() {
    mostraErrore("provinciaSpedizione-error", validaProvincia(this.value.trim()));
});

document.getElementById("form-registrazione").addEventListener("submit", function(e) {
    if (!validaForm("form-registrazione")) {
        e.preventDefault();
    }
});
</script>

</body>
</html>