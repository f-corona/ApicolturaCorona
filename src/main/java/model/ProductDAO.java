package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductDAO implements DAOInterface<ProductBean> {
    
    private static final String TABLE_NAME = "prodotto";
    
    @Override
    public ProductBean doRetrieveByKey(String code) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Prodotto = ?";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, Integer.parseInt(code));
        
        ResultSet rs = preparedStatement.executeQuery();
        ProductBean prodotto = null;
        
        if (rs.next()) {
            prodotto = getProdotto(rs);
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return prodotto;
    }
    
    @Override
    public Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
        List<ProductBean> prodotti = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE Cancellato = 0";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            prodotti.add(getProdotto(rs));
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return prodotti;
    }
    
   public Collection<ProductBean> doRetrieveByCategory(int idCategoria) throws SQLException {
        List<ProductBean> prodotti = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Categoria = ? AND Cancellato = 0";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, idCategoria);
        
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            prodotti.add(getProdotto(rs));
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return prodotti;
    }
    
    
    @Override
    public void doSave(ProductBean prodotto) throws SQLException {
        String insertSQL = "INSERT INTO " + TABLE_NAME + 
                          " (Nome, Descrizione, Prezzo, IVA, QuantitaDisponibile, ImmagineURL, ID_Categoria) " +
                          " VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        
        preparedStatement.setString(1, prodotto.getNome());
        preparedStatement.setString(2, prodotto.getDescrizione());
        preparedStatement.setBigDecimal(3, prodotto.getPrezzo());
        preparedStatement.setBigDecimal(4, prodotto.getIva());
        preparedStatement.setInt(5, prodotto.getQuantitaDisponibile());
        preparedStatement.setString(6, prodotto.getImmagineURL());
        preparedStatement.setInt(7, prodotto.getIdCategoria());
        
        preparedStatement.executeUpdate();
        connection.commit();
        
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
    }
    
    @Override
    public void doUpdate(ProductBean prodotto) throws SQLException {
    	String updateSQL = "UPDATE " + TABLE_NAME + 
                " SET Nome = ?, Descrizione = ?, Prezzo = ?, IVA = ?, " +
                " QuantitaDisponibile = ?, ImmagineURL = ?, ID_Categoria = ? " +
                " WHERE ID_Prodotto = ?";

Connection connection = DriverManagerConnectionPool.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

preparedStatement.setString(1, prodotto.getNome());
preparedStatement.setString(2, prodotto.getDescrizione());
preparedStatement.setBigDecimal(3, prodotto.getPrezzo());
preparedStatement.setBigDecimal(4, prodotto.getIva());
preparedStatement.setInt(5, prodotto.getQuantitaDisponibile());
preparedStatement.setString(6, prodotto.getImmagineURL());
preparedStatement.setInt(7, prodotto.getIdCategoria());
preparedStatement.setInt(8, prodotto.getId());

preparedStatement.executeUpdate();
connection.commit();

preparedStatement.close();
DriverManagerConnectionPool.releaseConnection(connection);
    }
    
    @Override
    public boolean doDelete(String code) throws SQLException {
        String updateSQL = "UPDATE " + TABLE_NAME + " SET Cancellato = 1 WHERE ID_Prodotto = ?";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
        preparedStatement.setInt(1, Integer.parseInt(code));
        
        int result = preparedStatement.executeUpdate();
        connection.commit();
        
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return result > 0;
    }
    
    private ProductBean getProdotto(ResultSet rs) throws SQLException {
        ProductBean prodotto = new ProductBean();
        prodotto.setId(rs.getInt("ID_Prodotto"));
        prodotto.setNome(rs.getString("Nome"));
        prodotto.setDescrizione(rs.getString("Descrizione"));
        prodotto.setPrezzo(rs.getBigDecimal("Prezzo")); // BigDecimal
        prodotto.setIva(rs.getBigDecimal("IVA"));       // BigDecimal
        prodotto.setQuantitaDisponibile(rs.getInt("QuantitaDisponibile"));
        prodotto.setCancellato(rs.getBoolean("Cancellato"));
        prodotto.setImmagineURL(rs.getString("ImmagineURL"));
        prodotto.setIdCategoria(rs.getInt("ID_Categoria"));
        return prodotto;
    }
}