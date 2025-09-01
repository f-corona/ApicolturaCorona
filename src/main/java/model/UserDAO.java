package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class UserDAO implements DAOInterface<UserBean> {
    
    private static final String TABLE_NAME = "utente";
    
    @Override
    public UserBean doRetrieveByKey(String code) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Utente = ?";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, Integer.parseInt(code));
        
        ResultSet rs = preparedStatement.executeQuery();
        UserBean user = null;
        
        if (rs.next()) {
            user = getUtente(rs);
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return user;
    }

    public UserBean doRetrieveByEmailPassword(String email, String hashedPassword) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE Email = ? AND Password = ?";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, hashedPassword);

        ResultSet rs = preparedStatement.executeQuery();
        UserBean user = null;
        
        if (rs.next()) {
            user = getUtente(rs);
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return user;
    }

    @Override
    public Collection<UserBean> doRetrieveAll(String order) throws SQLException {
        return null;
    }

    @Override
    public void doSave(UserBean user) throws SQLException {
        String insertSQL = "INSERT INTO " + TABLE_NAME + 
                           " (Email, Password, Nome, Cognome, Telefono, IndirizzoSpedizione, " +
                           " CittaSpedizione, CAPSpedizione, ProvinciaSpedizione, IsAdmin) " +
                           " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getNome());
        preparedStatement.setString(4, user.getCognome());
        preparedStatement.setString(5, user.getTelefono());
        preparedStatement.setString(6, user.getIndirizzoSpedizione());
        preparedStatement.setString(7, user.getCittaSpedizione());
        preparedStatement.setString(8, user.getCapSpedizione());
        preparedStatement.setString(9, user.getProvinciaSpedizione());
        preparedStatement.setBoolean(10, user.isAdmin());

        preparedStatement.executeUpdate();
        connection.commit();
        
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
    }

    @Override
    public void doUpdate(UserBean product) throws SQLException {
        
    }

    @Override
    public boolean doDelete(String code) throws SQLException {
        return false;
    }
    
    public boolean isRegistrato(String email) throws SQLException {
        String selectSQL = "SELECT COUNT(*) AS numUtenti FROM " + TABLE_NAME + " WHERE Email = ?";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, email);

        ResultSet rs = preparedStatement.executeQuery();
        boolean registrato = false;
        
        if (rs.next()) {
            registrato = rs.getInt("numUtenti") > 0;
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return registrato;
    }
    
    private UserBean getUtente(ResultSet rs) throws SQLException {
        UserBean user = new UserBean();
        user.setId(rs.getInt("ID_Utente"));
        user.setEmail(rs.getString("Email"));
        user.setPassword(rs.getString("Password"));
        user.setNome(rs.getString("Nome"));
        user.setCognome(rs.getString("Cognome"));
        user.setTelefono(rs.getString("Telefono"));
        user.setIndirizzoSpedizione(rs.getString("IndirizzoSpedizione"));
        user.setCittaSpedizione(rs.getString("CittaSpedizione"));
        user.setCapSpedizione(rs.getString("CAPSpedizione"));
        user.setProvinciaSpedizione(rs.getString("ProvinciaSpedizione"));
        user.setIsAdmin(rs.getBoolean("IsAdmin"));
        return user;
    }
}