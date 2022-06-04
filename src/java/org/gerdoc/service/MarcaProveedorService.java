/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.gerdoc.dao.Marca;
import org.gerdoc.dao.Producto;
import org.gerdoc.dao.Proveedor;
import static org.gerdoc.service.MySqlConnection.closeConnection;
import static org.gerdoc.service.MySqlConnection.getConnection;

/**
 *
 * @author 52553
 */
public class MarcaProveedorService {

    public MarcaProveedorService() {
    }
    public static List<Producto> getProductoList( )
    {
        List<Producto>productoList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Producto producto = null;
        
        try 
        {
            connection = getConnection( );
            if( connection == null )
            {
                return null;
            }
            statement = connection.createStatement( );
            if( statement == null )
            {
                return null;
            }
            resultSet = statement.executeQuery( "SELECT IdProducto, nombre,descripcion, url,PrecioPub,costo, marca,provedor FROM tblproducto INNER JOIN tblProvedor ON tblproducto.TBLProvedor_IdProvedor= TBLprovedor.IdProvedor INNER JOIN TBLMarca ON tblproducto.TBLMarca_IdMarca = TBLMarca.IdMarca" );
            if( resultSet == null )
            {
                return null;
            }
            productoList = new ArrayList<>();
            while( resultSet.next() )
            {
                producto = new Producto(new Marca(),new Proveedor());
                producto.setIdProducto(resultSet.getInt(1) );
                producto.setNombre(resultSet.getString(2) );
                producto.setDescripcion(resultSet.getString(3));
                producto.setUrl(resultSet.getString(4));
                producto.setPrecioPub(resultSet.getInt(5) );
                producto.setCosto(resultSet.getInt(6) );
                producto.getMarca().setMarca(resultSet.getString(7) );
                producto.getProveedor().setProveedor( resultSet.getString(8) );
                productoList.add(producto);
            }
            resultSet.close();
            closeConnection(connection);
            return productoList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addProducto( Producto producto )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( producto == null || producto.getIdProducto()== null )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "insert into tblProducto(nombre,descripcion,url,PrecioPub,costo,TBLMarca_idMarca,TBLProvedor_IdProvedor)values(?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setString(3, producto.getUrl());
            preparedStatement.setInt(4, producto.getPrecioPub());
            preparedStatement.setInt(5, producto.getCosto());
            preparedStatement.setInt(6, producto.getMarca().getIdMarca());
            preparedStatement.setInt(7, producto.getProveedor().getIdProveedor());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static Producto getProductoById( Integer id )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Producto producto = null;
        String sql = "SELECT * FROM TBLProducto WHERE IdProducto= ?";
        
        try 
        {
            connection = getConnection( );
            if( connection == null )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return null;
            }
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if( resultSet == null )
            {
                return null;
            }
            while( resultSet.next() )
            {
                producto = new Producto();
                producto.setIdProducto(resultSet.getInt(1) );
                producto.setNombre(resultSet.getString(2));
                producto.setDescripcion(resultSet.getString(3));
                producto.setUrl(resultSet.getString(4));
                producto.setPrecioPub(resultSet.getInt(5));
                producto.setCosto(resultSet.getInt(6));
            }
            preparedStatement.close();
            resultSet.close();
            closeConnection(connection);
            return producto;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateProducto( Producto producto )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( producto == null || producto.getIdProducto()== null )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update TBLProducto SET nombre=?,descripcion=?,url=?,preciopub=?,costo=?,TBLMarca_idMarca=?,TBLProvedor_IdProvedor=? WHERE IdProducto = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setString(3, producto.getUrl());
            preparedStatement.setInt(4, producto.getPrecioPub());
            preparedStatement.setInt(5, producto.getCosto());
            preparedStatement.setInt(6, producto.getMarca().getIdMarca());
            preparedStatement.setInt(7, producto.getProveedor().getIdProveedor());
            preparedStatement.setInt(8, producto.getIdProducto());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteProducto( Integer id )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( id == null || id == 0 )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "DELETE FROM TBLProducto WHERE IdProducto = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setInt(1, id);
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
}
