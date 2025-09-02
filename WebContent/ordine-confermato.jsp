<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordine Confermato</title>
<link rel="stylesheet" href="styles/global.css">
<link rel="stylesheet" href="styles/navbar.css">
<link rel="stylesheet" href="styles/footer.css">
</head>
<body>

<%@ include file="header.jsp" %>

<section class="hero">
<h1>Ordine Confermato!</h1>
<p>Grazie per il tuo acquisto.</p>
</section>

<section class="section">
<p>Il tuo ordine è stato registrato con successo.</p>

<p><a href="catalogo.jsp">Continua lo Shopping</a></p>
<p><a href="storico-ordini.jsp">Vedi I Miei Ordini</a></p>
</section>

<%@ include file="footer.jsp" %>
</body>
</html>