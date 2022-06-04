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
import static jdk.nashorn.internal.runtime.Debug.id;
import org.gerdoc.dao.RolUsuario;
import org.gerdoc.dao.Rol;
import org.gerdoc.dao.Usuario;
import static org.gerdoc.service.MySqlConnection.closeConnection;
import static org.gerdoc.service.MySqlConnection.getConnection;

/**
 *
 * @author 52553
 */
public class RolUsuarioService {

    public RolUsuarioService() {
    }
    public static List<RolUsuario> getRolUsuarioList( )
    {
        List<RolUsuario>rolUsuarioList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        RolUsuario rolUsuario = null;
        
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
            resultSet = statement.executeQuery( "SELECT " +
                    "ID," +
                    "USUARIO, " +
                    "PASSWORD," +
                    "CORREO,"+
                    "ROL,"+
                    "DESCRIPCION "+
                    "FROM TBL_ROL_TBL_USER "+
                    "INNER JOIN TBL_ROL ON "+
                    "TBL_ROL_TBL_USER.TBL_ROL_ROL = TBL_ROL.ROL "+
                    "INNER JOIN TBL_USUARIO ON " +
                    "TBL_ROL_TBL_USER.TBL_USUARIO_USUARIO = TBL_USUARIO.USUARIO" );
            if( resultSet == null )
            {
                return null;
            }
            rolUsuarioList = new ArrayList<>();
            while( resultSet.next() )
            {
                rolUsuario = new RolUsuario( new Rol( ) , new Usuario( ) );
                rolUsuario.setId( resultSet.getInt(1) );
                rolUsuario.getUsuario().setUsuario( resultSet.getString(2) );
                rolUsuario.getUsuario().setPassword( resultSet.getString(3) );
                rolUsuario.getUsuario().setCorreo( resultSet.getString(4) );
                rolUsuario.getRol( ).setRol( resultSet.getString(5) );
                rolUsuario.getRol( ).setDescripcion( resultSet.getString(6) );
                rolUsuarioList.add(rolUsuario);
            }
            resultSet.close();
            closeConnection(connection);
            return rolUsuarioList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addRolUsuario( RolUsuario rolUsuario )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( rolUsuario.getUsuario()== null || rolUsuario.getId()== null || rolUsuario.getRol()==null )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "INSERT INTO TBL_ROL_TBL_USER (TBL_ROL_ROL,TBL_USUARIO_USUARIO) VALUES(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, rolUsuario.getRol().getRol());
            preparedStatement.setString(2, rolUsuario.getUsuario().getUsuario());
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
    
    public static RolUsuario getRolUsuarioByRol( Integer id  )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        RolUsuario rolUsuario = null;
        String sql = "SELECT * FROM TBL_ROl_tbl_user WHERE Id= ?";
        
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
                rolUsuario = new RolUsuario();
                rolUsuario.setId(resultSet.getInt(1) );
                rolUsuario.getRol().setRol(resultSet.getString(2));
                rolUsuario.getUsuario().setUsuario(resultSet.getString(3));
            }
            preparedStatement.close();
            resultSet.close();
            closeConnection(connection);
            return rolUsuario;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateRolUsuario( RolUsuario rolUsuario )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( rolUsuario == null )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update FROM TBL_ROl_tbl_user  WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setInt(1, rolUsuario.getId());
            preparedStatement.setString(2, rolUsuario.getRol().getRol());
            preparedStatement.setString(3, rolUsuario.getUsuario().getUsuario());
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
    
    public static boolean deleteRolUsuario( Integer id )
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
            sql = "DELETE FROM TBL_ROl_tbl_user WHERE id = ?";
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
