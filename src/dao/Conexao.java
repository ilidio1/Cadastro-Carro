/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rig
 */
public class Conexao {
    public static Connection getConexao(){
        Connection conn=null;
        try { 
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste1","postgres","123456"); 
                        
       } catch(SQLException e) {
            e.printStackTrace();
       }
        return conn;
    } 
}
