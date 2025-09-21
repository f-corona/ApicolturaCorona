<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    UserBean user = (UserBean) session.getAttribute("currentUser");
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Apicoltura Corona</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="styles/global.css">
<link rel="stylesheet" href="styles/navbar.css">
<link rel="stylesheet" href="styles/footer.css">
<link rel="icon" href="images/apicolturaCoronaLogo.png" type="image/png">
<script type="text/javascript" src="scripts/carrello-ajax.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>

<section class="hero">
    <img src="images/apicolturaCoronaLogo.png" style="max-width:250px;">
    <h1>
        <%
           
            if(user != null) {
        %>
            Ciao, <%= user.getNome() %>.
        <%
            } else {
        %>
            Benvenuto!
        <%
            }
        %>
    </h1>
</section>


<section class="section">
<h2>Chi Siamo</h2>
  <p>
    Ci troviamo a Valva, in provincia di Salerno, dove le nostre api vivono in un ambiente collinare circondati da paesaggi stupendi e nelle vicininanze del Parco della Villa d'Ayala-Valva, una delle attrazioni turistiche più belle della Valle del Sele.
  </p>

  <div>
      <img src="images/api.jpg" style="width:30%; margin:5px;">
    <img src="images/miele.jpg" style="width:30%; margin:5px;">
    <img src="images/bomboniere.jpg" style="width:30%; margin:5px;">
  </div>
</section>

<%@ include file="footer.jsp" %>

</body>
</html>