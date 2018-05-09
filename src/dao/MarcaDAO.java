/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Marca;


/**
 *
 * @author Rig
 */
public class MarcaDAO implements IDAO {
  
   @Override
    public void cadastrarMarca() throws SQLException {        
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

           String sql = "insert into marca (nome) values " +
                    " ('Fiat')," +
                    " ('Wolkswagen'),"+
                   " ('Chevrolet')";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar cadastrar a a marca\n" + e.getMessage());
        }
    }

    @Override
    public void alterar(Object o) throws SQLException {
        Marca marca = (Marca) o;
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

            String sql = "update marca  set "                    
                    + "nome = ?,"                                     
                    + "where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores      
          
            stmt.setString(1, marca.getNome());            
            stmt.setInt(4, marca.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar alterar a marca. \n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        Marca marca = (Marca) o;
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

            String sql = "delete from marca "
                    + "where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setInt(1, marca.getId());
            //executa o sql (DML) para atualizar o banco. Pode ser um insert, update ou delete
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar remover o marca. \n" + e.getMessage());
        }
    }

    @Override
    public List listarTodos() throws SQLException {
        List lista = new ArrayList();
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            String sql = "select * from marca";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet cs = stmt.executeQuery();
            while (cs.next()) {
                Marca c = new Marca ();
                c.setNome(cs.getString("nome"));                
                c.setId(cs.getInt("id"));
                lista.add(c);
            }

            cs.close();
            stmt.close();
            conn.close();
            return lista;
        } catch (SQLException e) {
            throw new SQLException("Erro ao recuperar a lista de marcas \n" + e.getMessage());
        }
    }

    @Override
    public Object listarPorId(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            String sql = "select * from marca where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet cs = stmt.executeQuery();
            cs.next();
            Marca m = new Marca();
            m.setNome(cs.getString("nome"));               
                m.setId(cs.getInt("id"));
            
            cs.close();
            stmt.close();
            conn.close();
            return m;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a marca. \n" + e.getMessage());
        }
    }

    @Override
    public void cadastrar(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
