package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CategoryDAO implements DAOInterface<CategoryBean> {
    
    private static final String TABLE_NAME = "categoria";
    
    @Override
    public CategoryBean doRetrieveByKey(String code) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Categoria = ?";
        
        try (Connection connection = DriverManagerConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            
            preparedStatement.setInt(1, Integer.parseInt(code));
            
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return getCategoria(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    public Collection<CategoryBean> doRetrieveAll(String order) throws SQLException {
        List<CategoryBean> categorie = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TABLE_NAME;
        
        if (order != null && !order.isEmpty()) {
            selectSQL += " ORDER BY " + order;
        }
        
        try (Connection connection = DriverManagerConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    categorie.add(getCategoria(rs));
                }
            }
        }
        return categorie;
    }
    
    @Override
    public void doSave(CategoryBean categoria) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String insertSQL = "INSERT INTO " + TABLE_NAME + " (Nome) VALUES (?)";
        
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            
            preparedStatement.setString(1, categoria.getNome());
            
            preparedStatement.executeUpdate();
            connection.commit();
            
        } finally {
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
    public void doUpdate(CategoryBean categoria) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String updateSQL = "UPDATE " + TABLE_NAME + " SET Nome = ? WHERE ID_Categoria = ?";
        
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
            
            preparedStatement.setString(1, categoria.getNome());
            preparedStatement.setInt(2, categoria.getId());
            
            preparedStatement.executeUpdate();
            connection.commit();
            
        } finally {
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
    public boolean doDelete(String code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE ID_Categoria = ?";
        
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            
            preparedStatement.setInt(1, Integer.parseInt(code));
            
            int result = preparedStatement.executeUpdate();
            connection.commit();
            
            return result > 0;
            
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }
    
    private CategoryBean getCategoria(ResultSet rs) throws SQLException {
        CategoryBean categoria = new CategoryBean();
        categoria.setId(rs.getInt("ID_Categoria"));
        categoria.setNome(rs.getString("Nome"));
        return categoria;
    }
}