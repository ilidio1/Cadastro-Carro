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
import modelo.Carro;
import modelo.Marca;

/**
 *
 * @author Rig
 */
public class CarroDAO implements IDAO {
     @Override
    public void cadastrar(Object o) throws SQLException {
        Carro carro = (Carro) o;
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

            String sql = "insert into carro (descricao,cor,ano,marca_id) "
                    + "values (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores 1    
            
            stmt.setString(1, carro.getDescricao());
            stmt.setString(2, carro.getCor());
            stmt.setInt(3, carro.getAno());
            stmt.setInt(4, carro.getMarca().getId());
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar cadastrar a carro. \n" + e.getMessage());
        }
    }

    @Override
    public void alterar(Object o) throws SQLException {
        Carro carro = (Carro) o;
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

            String sql = "update carro set "                    
                    + "descricao = ?, "
                    + "cor= ?, "
                    + "ano= ?, "     
                    + "marca_id= ? " 
                    + "where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores      
          
            stmt.setString(1, carro.getDescricao());
            stmt.setString(2, carro.getCor());
            stmt.setInt(3, carro.getAno());
            stmt.setInt(4, carro.getMarca().getId());
            stmt.setInt(5, carro.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar alterar o veiculo. \n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        Carro carro = (Carro) o;
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

            String sql = "delete from carro "
                    + "where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setInt(1, carro.getId());
            //executa o sql (DML) para atualizar o banco. Pode ser um insert, update ou delete
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Eroo ao tentar remover o carro. \n" + e.getMessage());
        }
    }

    @Override
    public List listarTodos() throws SQLException {
        List lista = new ArrayList();
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            String sql = "select carro.id, ano, descricao, cor, marca.id AS marca_id, marca.nome AS marca_nome " 
                    + "FROM carro " 
                    + "JOIN marca ON (marca.id = carro.marca_id);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet cs = stmt.executeQuery();
            while (cs.next()) {
                Carro c = new Carro ();
                Marca m = new Marca();
                c.setDescricao(cs.getString("descricao"));
                c.setCor(cs.getString("cor"));
                c.setAno(cs.getInt("Ano"));
                c.setId(cs.getInt("id"));
                m.setId(cs.getInt("marca_id"));
                m.setNome(cs.getString("marca_nome"));
                c.setMarca(m);
                lista.add(c);
            }

            cs.close();
            stmt.close();
            conn.close();
            return lista;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a lista de carros \n" + e.getMessage());
        }
    }

    @Override
    public Object listarPorId(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            String sql = "select * from carro where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet cs = stmt.executeQuery();
            cs.next();
            Carro c = new Carro();
            c.setDescricao(cs.getString("descricao"));
                c.setCor(cs.getString("cor"));
                c.setAno(cs.getInt("Ano"));
                c.setId(cs.getInt("id"));
            

            cs.close();
            stmt.close();
            conn.close();
            return c;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a carro. \n" + e.getMessage());
        }
    }
    
     public List listarMarcas() throws SQLException {
        MarcaDAO dao = new MarcaDAO();
        return dao.listarTodos();
    }

    

    @Override
    public void cadastrarMarca() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
