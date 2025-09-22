package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class OrderDAO {
    
    private static final String TABLE_NAME = "ordine";
    
    public int doSave(OrderBean ordine) throws SQLException {
        String insertSQL = "INSERT INTO " + TABLE_NAME + 
                          " (ID_Utente, DataOrdine, Stato, Totale) VALUES (?, ?, ?, ?)";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        
        preparedStatement.setInt(1, ordine.getIdUtente());
        preparedStatement.setTimestamp(2, ordine.getDataOrdine());
        preparedStatement.setString(3, ordine.getStato());
        preparedStatement.setBigDecimal(4, ordine.getTotale());
        
        preparedStatement.executeUpdate();
        
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        int nuovoIdOrdine = 0;
        if (generatedKeys.next()) {
            nuovoIdOrdine = generatedKeys.getInt(1);
        }
        
        salvaDettagliOrdine(nuovoIdOrdine, ordine.getProdotti(), connection);
        connection.commit();
        
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return nuovoIdOrdine;
    }
    
    private void salvaDettagliOrdine(int idOrdine, ArrayList<ProductBean> prodotti, Connection connection) throws SQLException {
        String insertDetailSQL = "INSERT INTO dettaglio_ordine (ID_Ordine, ID_Prodotto, QuantitaAcquistata, PrezzoAcquisto) VALUES (?, ?, ?, ?)";
        PreparedStatement detailStatement = connection.prepareStatement(insertDetailSQL);
        
        for (ProductBean prodotto : prodotti) {
            detailStatement.setInt(1, idOrdine);
            detailStatement.setInt(2, prodotto.getId());
            detailStatement.setInt(3, prodotto.getQuantitaDisponibile());
            detailStatement.setBigDecimal(4, prodotto.getPrezzoConIva());
            
            detailStatement.executeUpdate();
        }
        
        detailStatement.close();
    }
    
    public Collection<OrderBean> doRetrieveByUser(int idUtente) throws SQLException {
        Collection<OrderBean> ordini = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Utente = ? ORDER BY DataOrdine DESC";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, idUtente);
        
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            OrderBean ordine = new OrderBean();
            ordine.setIdOrdine(rs.getInt("ID_Ordine"));
            ordine.setIdUtente(rs.getInt("ID_Utente"));
            ordine.setDataOrdine(rs.getTimestamp("DataOrdine"));
            ordine.setStato(rs.getString("Stato"));
            ordine.setTotale(rs.getBigDecimal("Totale"));
            ordini.add(ordine);
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return ordini;
    }
    
 public Collection<OrderBean> doRetrieveAll() throws SQLException {
        Collection<OrderBean> ordini = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " ORDER BY DataOrdine DESC";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            OrderBean ordine = new OrderBean();
            ordine.setIdOrdine(rs.getInt("ID_Ordine"));
            ordine.setIdUtente(rs.getInt("ID_Utente"));
            ordine.setDataOrdine(rs.getTimestamp("DataOrdine"));
            ordine.setStato(rs.getString("Stato"));
            ordine.setTotale(rs.getBigDecimal("Totale"));
            ordini.add(ordine);
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return ordini;
    }

}