package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDAO implements DAOInterface<UserBean> {
	 private static final String TABLE_NAME = "utente";
	@Override
	public UserBean doRetrieveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
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

        // Query INSERT con TUTTI i campi NON AUTO_INCREMENT della tabella 'utente'
        
        String insertSQL = "INSERT INTO " + TABLE_NAME + 
                           " (Email, Password, Nome, Cognome, Telefono, IndirizzoSpedizione, " +
                           " CittaSpedizione, CAPSpedizione, ProvinciaSpedizione, IsAdmin) " +
                           " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection(); // Ottieni una connessione dal tuo pool
            // NON chiamare connection.setAutoCommit(true) qui, il pool lo gestisce già a false.
            preparedStatement = connection.prepareStatement(insertSQL);

            // Imposta TUTTI i 12 parametri in base al tuo UserBean completo
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

            preparedStatement.executeUpdate(); // Esegui l'inserimento
            connection.commit(); // Esegui il commit della transazione

        } finally {
            // Chiudi le risorse correttamente
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    DriverManagerConnectionPool.releaseConnection(connection); // Rilascia la connessione al pool
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
		Connection connection = null ; 
		PreparedStatement preparedstatement = null ; 
		
		String selectSQL ;  
		
		if(email == null)
			selectSQL = "select count(*) as numUtenti FROM " + TABLE_NAME ; 
		else
			selectSQL = "select count(*) as numUtenti FROM " + TABLE_NAME + " WHERE email = ? ; "; 
		
		try {
				connection = DriverManagerConnectionPool.getConnection() ; 
				preparedstatement = connection.prepareStatement(selectSQL) ;
				preparedstatement.setString(1, email);
				ResultSet rs = preparedstatement.executeQuery() ; 
				rs.next();
				if(rs.getInt("numUtenti") > 0) {
					return true;
				}
				return false;
		}finally {
			try {
				if(preparedstatement != null)
					preparedstatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}