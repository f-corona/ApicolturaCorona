<%@
	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registrazione</title>
        <link rel="stylesheet" href="styles/admin.css">
        <link rel="stylesheet" href="styles/style.css">
        <link rel="icon" type="image/x-icon" href="images/apicolturaCoronaIcon.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%
            // parametro restituito se sei gi registrato quindi mostra l'errore
            // request get parameter map restituisce la mappa dei parametri e controlliamo se c' il parametro corrispondente
            if(request.getParameterMap().containsKey("ar")){
        %>
                <div class="containerMessaggio">
                    <div id="messaggioConferma">
                        <img src="images/error.png" class="erroreMessaggio">
                        <input type="image" src="images/close.png" class="chiudiMessaggio" onclick="nascondiMessaggio()">
                        <span>Questa email risulta gi registrata</span>
                    </div>
                </div>
        <%
            }
        %>
        <img src='images/apicolturaCoronaLogo.png' class='logo'>
        <div id='contenitoreForm'>
            <form action="RegistrazioneServlet" method='POST' id='form'>
                <img src='images/social.png' class='social'>
                <br>
                <input type="text" name="nome" id="nome" placeholder=" Nome" class='dati'><br><br>
                <input type="text" name="cognome" id="cognome" placeholder=" Cognome" class='dati'><br><span class="formError" id="errorNominativo">Il nome e il cognome devono contenere solo lettere</span><br>
                <input type="email" name="email" id="email" placeholder=" example@gmail.com" class='dati'><br><span class="formError" id="errorMail">È necessario inserire un'email valida</span><br>
                <input type="password" name="password" id="password" placeholder=" Password" class='dati'><br><span id="indicazionePass" class='tipPassword'>La password deve contenere almeno 3 caratteri (una lettera, un numero e un simbolo)</span><span class="formError" id="errorPass">Devi inserire una password che rispetti le regole sopraindicate</span><br>
                <input type="password" name="confermaPassword" id="password2" placeholder=" Conferma Password" class='dati'><br><span class="formError" id="errorPass2">Le password non corrispondono</span><br><br>

                <input type="text" name="telefono" id="telefono" placeholder=" Telefono (Opzionale)" class='dati'><br><br>
                <input type="text" name="indirizzoSpedizione" id="indirizzoSpedizione" placeholder=" Indirizzo Spedizione" class='dati'><br><br>
                <input type="text" name="cittaSpedizione" id="cittaSpedizione" placeholder=" Città Spedizione" class='dati'><br><br>
                <input type="text" name="capSpedizione" id="capSpedizione" placeholder=" CAP Spedizione" class='dati' maxlength="5"><br><br>
                <input type="text" name="provinciaSpedizione" id="provinciaSpedizione" placeholder=" Provincia Spedizione (es. SA)" class='dati' maxlength="2"><br><br>
                
                

                <input type="submit" value="Registrati" class='bottone'>
            </form>
            <br>
            <span id= "account">
                Hai già un account? <a href='LoginPage.jsp'>Vai a login</a><br>
            </span>
        </div>
	</body>
        <script src="scripts/script.js"></script>     
</html>