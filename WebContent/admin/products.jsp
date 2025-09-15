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

    // Controllo se sto modificando un prodotto
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

<div class="container-admin">

<form action="../ProductManagementServlet" method="post" class="form-admin">
    <% if (prodottoDaModificare != null) { %>
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= prodottoDaModificare.getId() %>">
        <h3>Modifica Prodotto</h3>
    <% } else { %>
        <input type="hidden" name="action" value="insert">
        <h3>Inserisci Nuovo Prodotto</h3>
    <% } %>

    <div class="gruppo">
        <label>Nome:</label>
        <input type="text" name="nome" required
               value="<%= prodottoDaModificare != null ? prodottoDaModificare.getNome() : "" %>">
    </div>

    <div class="gruppo">
        <label>Descrizione:</label>
        <textarea name="descrizione" rows="3" required><%= prodottoDaModificare != null ? prodottoDaModificare.getDescrizione() : "" %></textarea>
    </div>

    <div class="gruppo">
        <label>Prezzo (€):</label>
        <input type="text" name="prezzo" required
               value="<%= prodottoDaModificare != null ? prodottoDaModificare.getPrezzo() : "" %>">
    </div>

    <div class="gruppo">
        <label>IVA (%):</label>
        <input type="text" name="iva" required
               value="<%= prodottoDaModificare != null ? prodottoDaModificare.getIva() : "" %>">
    </div>

    <div class="gruppo">
        <label>Quantità disponibile:</label>
        <input type="number" name="quantita" required
               value="<%= prodottoDaModificare != null ? prodottoDaModificare.getQuantitaDisponibile() : "" %>">
    </div>

    <div class="gruppo">
        <label>Categoria:</label>
        <select name="categoria" required>
            <option value="">Seleziona Categoria</option>
            <% for (CategoryBean categoria : categorie) { %>
                <option value="<%= categoria.getId() %>" 
                    <%= prodottoDaModificare != null && prodottoDaModificare.getIdCategoria() == categoria.getId() ? "selected" : "" %>>
                    <%= categoria.getNome() %>
                </option>
            <% } %>
        </select>
    </div>

    <div class="gruppo">
        <label>URL Immagine:</label>
        <input type="text" name="immagineURL"
               value="<%= prodottoDaModificare != null ? prodottoDaModificare.getImmagineURL() : "" %>">
    </div>

    <input type="submit" value="<%= prodottoDaModificare != null ? "Aggiorna" : "Inserisci" %>" class="bottone">

    <% if (prodottoDaModificare != null) { %>
        <a href="products.jsp">Annulla</a>
    <% } %>
</form>

<h3>Prodotti Esistenti</h3>
<ul>
    <% for (ProductBean prodotto : prodotti) { %>
        <li>
            <strong><%= prodotto.getNome() %></strong> 
             -
            <a href="products.jsp?edit=<%= prodotto.getId() %>">Modifica</a> -  
            <a href="../ProductManagementServlet?action=delete&id=<%= prodotto.getId() %>" 
               onclick="return confirm('Vuoi cancellare questo prodotto?')">Cancella</a>
        </li>
    <% } %>
</ul>

<p><a href="dashboard.jsp">Torna alla Dashboard</a></p>
</div>

<footer class="footer">
    <h3>Contattaci</h3>
</footer>

</body>
</html>
