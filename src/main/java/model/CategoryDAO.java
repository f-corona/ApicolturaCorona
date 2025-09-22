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
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, Integer.parseInt(code));
        
        ResultSet rs = preparedStatement.executeQuery();
        CategoryBean categoria = null;
        
        if (rs.next()) {
            categoria = getCategoria(rs);
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return categoria;
    }
    
    @Override
    public Collection<CategoryBean> doRetrieveAll(String order) throws SQLException {
        List<CategoryBean> categorie = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TABLE_NAME;
        

        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        ResultSet rs = preparedStatement.executeQuery();
        
        while (rs.next()) {
            categorie.add(getCategoria(rs));
        }
        
        rs.close();
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
        
        return categorie;
    }
    
    @Override
    public void doSave(CategoryBean categoria) throws SQLException {
        String insertSQL = "INSERT INTO " + TABLE_NAME + " (Nome) VALUES (?)";
        
        Connection connection = DriverManagerConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        
        preparedStatement.setString(1, categoria.getNome());
        preparedStatement.executeUpdate();
        connection.commit();
        
        preparedStatement.close();
        DriverManagerConnectionPool.releaseConnection(connection);
    }
    
    @Override
    public void doUpdate(CategoryBean categoria) throws SQLException {
       //da fare
    }
    
    @Override
    public boolean doDelete(String code) throws SQLException {
        //da fare
        return false;
    }
    
    private CategoryBean getCategoria(ResultSet rs) throws SQLException {
        CategoryBean categoria = new CategoryBean();
        categoria.setId(rs.getInt("ID_Categoria"));
        categoria.setNome(rs.getString("Nome"));
        return categoria;
    }
}