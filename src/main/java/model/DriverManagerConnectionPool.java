package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

    private static List<Connection> freeDbConnections;
        static {
                    // lista delle connessioni
                    freeDbConnections = new LinkedList<Connection>();
                    try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                        } catch (ClassNotFoundException e) {
                            System.out.println("DB driver not found!");
                        }
        }

        // crea e restituisce la connessione al db
        private static Connection createDBConnection() throws SQLException {
            // Configurare host, porta, nome database, username e password tramite variabili d'ambiente o file esterno.
            String dbUrl = requireEnv("APICOLTURA_DB_URL");
            String username = requireEnv("APICOLTURA_DB_USERNAME");
            String password = requireEnv("APICOLTURA_DB_PASSWORD");

            Connection newConnection = DriverManager.getConnection(dbUrl, username, password);
            newConnection.setAutoCommit(false);

            return newConnection;
            }

        private static String requireEnv(String key) throws SQLException {
            String value = System.getenv(key);
            if (value == null || value.isBlank()) {
                throw new SQLException("Missing required environment variable: " + key);
            }
            return value;
        }

        // ottiene una connessione dalla lista
        public static synchronized Connection getConnection() throws SQLException {

            Connection connection;

            // se la lista non è vuota la assegna e la rimuove dalla lista delle libere
            if (! freeDbConnections.isEmpty()) {
                connection = (Connection) freeDbConnections.get(0);
                DriverManagerConnectionPool.freeDbConnections.remove(0);
                try {
                        // se la connessione è chiusa, richiama il metodo getConnection
                        if (connection.isClosed())
                            connection = DriverManagerConnectionPool.getConnection();
                }catch (SQLException e) {
                    connection = DriverManagerConnectionPool.getConnection();
                }

            } else //se la lista è vuota (non esistono connessioni libere) ne creiamo una nuova 
                connection = DriverManagerConnectionPool.createDBConnection();

            return connection;
        }

        // rilascia la connessione aggiungendola alla lista delle libere
        public static synchronized void releaseConnection(Connection connection) {
            DriverManagerConnectionPool.freeDbConnections.add(connection);
            }
    }
