package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    public void doSave(ProdottoBean prodotto) throws SQLException {
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO prodotto (Nome, Descrizione, Prezzo, QuantitaDisponibile, ImmagineURL, ID_Categoria) VALUES (?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, prodotto.getNome());
            ps.setString(2, prodotto.getDescrizione());
            ps.setDouble(3, prodotto.getPrezzo());
            ps.setInt(4, prodotto.getQuantitaDisponibile());
            ps.setString(5, prodotto.getImmagineURL());
            ps.setInt(6, prodotto.getIdCategoria());

            ps.executeUpdate();
            con.commit();
        }
    }

    public ProdottoBean doRetrieveByKey(int id) throws SQLException {
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM prodotto WHERE ID_Prodotto = ?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractProdotto(rs);
                }
            }
        }
        return null;
    }

    public List<ProdottoBean> doRetrieveAll() throws SQLException {
        List<ProdottoBean> prodotti = new ArrayList<>();
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM prodotto");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                prodotti.add(extractProdotto(rs));
            }
        }
        return prodotti;
    }

    public List<ProdottoBean> doRetrieveByCategoria(int idCategoria) throws SQLException {
        List<ProdottoBean> prodotti = new ArrayList<>();
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM prodotto WHERE ID_Categoria = ?")) {

            ps.setInt(1, idCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prodotti.add(extractProdotto(rs));
                }
            }
        }
        return prodotti;
    }

    public void doUpdate(ProdottoBean prodotto) throws SQLException {
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "UPDATE prodotto SET Nome=?, Descrizione=?, Prezzo=?, QuantitaDisponibile=?, ImmagineURL=?, ID_Categoria=? WHERE ID_Prodotto=?")) {

            ps.setString(1, prodotto.getNome());
            ps.setString(2, prodotto.getDescrizione());
            ps.setDouble(3, prodotto.getPrezzo());
            ps.setInt(4, prodotto.getQuantitaDisponibile());
            ps.setString(5, prodotto.getImmagineURL());
            ps.setInt(6, prodotto.getIdCategoria());
            ps.setInt(7, prodotto.getIdProdotto());

            ps.executeUpdate();
            con.commit();
        }
    }

    public void doDelete(int id) throws SQLException {
        try (Connection con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM prodotto WHERE ID_Prodotto = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
        }
    }

    private ProdottoBean extractProdotto(ResultSet rs) throws SQLException {
        ProdottoBean p = new ProdottoBean();
        p.setIdProdotto(rs.getInt("ID_Prodotto"));
        p.setNome(rs.getString("Nome"));
        p.setDescrizione(rs.getString("Descrizione"));
        p.setPrezzo(rs.getDouble("Prezzo"));
        p.setQuantitaDisponibile(rs.getInt("QuantitaDisponibile"));
        p.setImmagineURL(rs.getString("ImmagineURL"));
        p.setIdCategoria(rs.getInt("ID_Categoria"));
        return p;
    }
}
