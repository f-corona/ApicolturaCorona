<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UserBean, model.ProductBean, model.ProductDAO, model.CategoryBean, model.CategoryDAO, java.util.Collection" %>
<%
    // Controllo admin
    UserBean adminUser = (UserBean) session.getAttribute("currentUser");
    if (adminUser == null || !adminUser.isAdmin()) {
        response.sendRedirect("../login.jsp");
        return;
    }

    // Recupero categorie e prodotti
    CategoryDAO categoryDAO = new CategoryDAO();
    Collection<CategoryBean> categorie = categoryDAO.doRetrieveAll("Nome");

    ProductDAO productDAO = new ProductDAO();
    Collection<ProductBean> prodotti = productDAO.doRetrieveAll(null);

    
    String editId = request.getParameter("edit");
    ProductBean prodottoDaModificare = null;
    if (editId != null) {
        prodottoDaModificare = productDAO.doRetrieveByKey(editId);
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestione Prodotti - Apicoltura Corona</title>
<link rel="stylesheet" href="../styles/global.css">
<link rel="stylesheet" href="../styles/admin.css">
<link rel="stylesheet" href="../styles/footer.css">
</head>
<body>

<section class="hero">
    <h1>Gestione Prodotti</h1>
</section>

   
<form action="../ProductManagementServlet" method="post" class="form-admin">
    <% if (prodottoDaModificare != null) { %>
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= prodottoDaModificare.getId() %>">
        <h3>Modifica Prodotto</h3>
    <% } else { %>
        <input type="hidden" name="action" value="insert">
        <h3>Inserisci Nuovo Prodotto</h3>
    <% } %>

    <div>
        <label>Nome:</label>
        <input type="text" name="nome" required
               value="<% if (prodottoDaModificare != null) { out.print(prodottoDaModificare.getNome()); } %>">
    </div>

    <div>
        <label>Descrizione:</label>
        <input type="text" name="descrizione" required
               value="<% if (prodottoDaModificare != null) { out.print(prodottoDaModificare.getDescrizione()); } %>">
    </div>

    <div>
        <label>Prezzo (€):</label>
        <input type="text" name="prezzo" required
               value="<% if (prodottoDaModificare != null) { out.print(prodottoDaModificare.getPrezzo()); } %>">
    </div>

    <div>
        <label>IVA (%):</label>
        <input type="text" name="iva" required
               value="<% if (prodottoDaModificare != null) { out.print(prodottoDaModificare.getIva()); } %>">
    </div>

    <div>
        <label>Quantità disponibile:</label>
        <input type="number" name="quantita" required
               value="<% if (prodottoDaModificare != null) { out.print(prodottoDaModificare.getQuantitaDisponibile()); } else { out.print(0); } %>">
    </div>

    <div>
        <label>Disponibile (0 = SI, 1 = NO):</label>
        <input type="number" name="cancellato" required
               value="<% if (prodottoDaModificare != null) { out.print(prodottoDaModificare.isCancellato() ? 1 : 0); } else { out.print(0); } %>">
    </div>

    <div>
        <label>ID Categoria (1 = Alveare, 2 = Materiale):</label>
        <input type="number" name="categoria" required
               value="<% if (prodottoDaModificare != null) { out.print(prodottoDaModificare.getIdCategoria()); } %>">
    </div>

    <div>
        <label>URL Immagine:</label>
        <input type="text" name="immagineURL"
               value="<% if (prodottoDaModificare != null) { out.print(prodottoDaModificare.getImmagineURL()); } %>">
    </div>

    <input type="submit" value="<% if (prodottoDaModificare != null) { out.print("Aggiorna"); } else { out.print("Inserisci"); } %>" class="bottone">

    <% if (prodottoDaModificare != null) { %>
        <a href="products.jsp">Annulla</a>
    <% } %>
</form>


<div class="div-admin">
<h3>Prodotti Esistenti</h3>
<table class="tabellaAdmin">
    <thead>
        <tr>
            <th>Nome</th>
            <th>Prezzo (€)</th>
            <th>IVA (%)</th>
            <th>Quantità</th>
            <th>Disponibile</th>
            <th>Azioni</th>
        </tr>
    </thead>
    <tbody>
        <% for (ProductBean prodotto : prodotti) { %>
        <tr>
            <td><%= prodotto.getNome() %></td>
            <td><%= prodotto.getPrezzo() %></td>
            <td><%= prodotto.getIva() %></td>
            <td><%= prodotto.getQuantitaDisponibile() %></td>
            <td><% if (prodotto.isCancellato()) { %>
            			NO
    							<% } else { %>
       						SI
    							<% } %></td>
            <td>
                <a href="products.jsp?edit=<%= prodotto.getId() %>">Modifica</a> |
                <a href="../ProductManagementServlet?action=delete&id=<%= prodotto.getId() %>"
                   onclick="return confirm('Vuoi cancellare questo prodotto?')">Cancella</a>
            </td>
        </tr>
        <% } %>
    </tbody>
</table>



    <p><a href="dashboard.jsp">Torna alla Dashboard</a></p>
</div>

<%@ include file="../footer.jsp" %>
</body>
</html>
