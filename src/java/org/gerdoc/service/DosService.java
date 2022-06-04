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
import org.gerdoc.dao.Dos;

/**
 *
 * @author gerdoc
 */
public class DosService 
{

    public DosService() 
    {
    }
    
    public List<Dos> getDosList( )
    {
        List<Dos>dosList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Dos dos = null;
        
        try 
        {
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return null;
            }
            statement = connection.createStatement( );
            if( statement == null )
            {
                return null;
            }
            resultSet = statement.executeQuery( "SELECT * FROM TBL_DOS" );
            if( resultSet == null )
            {
                return null;
            }
            dosList = new ArrayList<>();
            while( resultSet.next() )
            {
                dos = new Dos();
                dos.setId( resultSet.getInt(1) );
                dos.setCampo1(resultSet.getInt(2) );
                dos.setCampo2(resultSet.getString(3) );
                dosList.add(dos);
            }
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return dosList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean addDos( Dos dos )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO TBL_DOS(CAMPO1,CAMPO2) VALUES(?,?)";
        int row = 0;
        try 
        {
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setInt(1, dos.getCampo1());
            preparedStatement.setString(2, dos.getCampo2());            
            row = preparedStatement.executeUpdate();
            MySqlConnection.closeConnection(connection);
            return row == 1;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
}
