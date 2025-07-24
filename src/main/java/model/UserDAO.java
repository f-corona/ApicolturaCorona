package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;



public class UserDAO implements DAOInterface<UserBean> {
	 
	private static final String TABLE_NAME = "utente";
	
	public UserBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
				return null;
	}
	
	@Override
	public UserBean doRetrieveByEmailPassword(String email, String hashedPassword) throws SQLException {
	    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE Email = ? AND Password = ?";
	    
	    try (Connection connection = DriverManagerConnectionPool.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
	        
	        preparedStatement.setString(1, email);
	        preparedStatement.setString(2, hashedPassword);

	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            if (rs.next()) {
	                return getUtente(rs);
	            }
	        }
	    }
	    
	    //se non trovo utenti
	    return null;
	}

	@Override
	public Collection<UserBean> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(UserBean user) throws SQLException { 
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        
        
        String insertSQL = "INSERT INTO " + TABLE_NAME + 
                           " (Email, Password, Nome, Cognome, Telefono, IndirizzoSpedizione, " +
                           " CittaSpedizione, CAPSpedizione, ProvinciaSpedizione, IsAdmin) " +
                           " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(); 
           
            preparedStatement = connection.prepareStatement(insertSQL);

           
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNome());
            preparedStatement.setString(4, user.getCognome());
            preparedStatement.setString(5, user.getTelefono()); // Campo opzionale
            preparedStatement.setString(6, user.getIndirizzoSpedizione());
            preparedStatement.setString(7, user.getCittaSpedizione());
            preparedStatement.setString(8, user.getCapSpedizione());
            preparedStatement.setString(9, user.getProvinciaSpedizione());
            preparedStatement.setBoolean(10, user.isIsAdmin()); // Campo con default nel DB

            preparedStatement.executeUpdate(); // inserimento
            connection.commit(); // ommit della transazione

        } finally {
            // Chiudi risorse
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

	@Override
	public void doUpdate(UserBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doDelete(String code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public boolean isRegistrato(String email) throws SQLException {
		String selectSQL = "SELECT COUNT(*) AS numUtenti FROM " + TABLE_NAME + " WHERE Email = ?";
		try (Connection connection = DriverManagerConnectionPool.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

	            preparedStatement.setString(1, email);

	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("numUtenti") > 0;
	                }
	            }
	        }
	        return false;
	    }
	
	
	private UserBean getUtente(ResultSet rs) throws SQLException {
        UserBean user = new UserBean();
        user.setId(rs.getInt("ID_Utente"));
        user.setEmail(rs.getString("Email"));
        user.setPassword(rs.getString("Password")); // hashed
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