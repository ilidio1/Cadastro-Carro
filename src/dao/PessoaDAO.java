/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import modelo.Carro;
import modelo.Pessoa;

/**
 *
 * @author Rig
 */
public class PessoaDAO implements IDAO {
     @Override
    public void cadastrar(Object o) throws SQLException {
        Pessoa pessoa = (Pessoa) o;
        
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

            String sql = "insert into pessoa (nome,cpf,datanasc,carro) "
                    + "values (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3,pessoa.getDatanasc().toString());            
           if(pessoa.getCarro() != null){
            stmt.setInt(4, pessoa.getCarro().getId());
           
           }
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar cadastrar a pessoa \n" + e.getMessage());
        }
    }

    @Override
    public void alterar(Object o) throws SQLException {
        Pessoa pessoa = (Pessoa) o;
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

            String sql = "update pessoa  set "
                    + "nome = ?, "
                    + "cpf = ?,"
                    + "datanasc= ?"                                        
                    + "where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3,pessoa.getDatanasc().toString());
            stmt.setInt(4, pessoa.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar alterar cadastro \n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        Pessoa pessoa = (Pessoa) o;
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

            String sql = "delete from pessoa "
                    + "where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores                     
            stmt.setInt(1, pessoa.getId());
            //executa o sql (DML) para atualizar o banco. Pode ser um insert, update ou delete
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Eroo ao tentar remover a pessoa. \n" + e.getMessage());
        }
    }

    @Override
    public List listarTodos() throws SQLException {
        List lista = new ArrayList();
        Connection conn = null;
        LocalDate nascimento;
        String data;
        try {
            conn = Conexao.getConexao();
            String sql = "SELECT * " +
            "FROM pessoa " +
            "LEFT JOIN carro ON (carro.id = pessoa.carro);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa ();
                Carro carro = new Carro();
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                data = (rs.getString("datanasc"));               
                pessoa.setId(rs.getInt("id"));
                
                carro.setDescricao(rs.getString("descricao"));
                carro.setAno(rs.getInt("ano"));
               
                pessoa.setCarro(carro);
                
                
                String ano = data.substring(0, 4);
                String mes = data.substring(5, 7);
                String dia = data.substring(8, 10);
                nascimento = (LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia)));  //Receber data

                pessoa.setDatanasc(nascimento);
                lista.add(pessoa);
            }

            rs.close();
            stmt.close();
            conn.close();
            return lista;
        } catch (SQLException e) {
            throw new SQLException("Erro ao recuperar a lista de pessoas\n" + e.getMessage());
        }
    }

    @Override
    public Object listarPorId(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            String sql = "select * from pessoa where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet ps = stmt.executeQuery();
            ps.next();
            Pessoa p = new Pessoa ();
            p.setNome(ps.getString("nome"));
            p.setCpf(ps.getString("cpf"));
                //p.setDatanasc(ps.getString("datanasc"));                
                p.setId(ps.getInt("id"));
            

            ps.close();
            stmt.close();
            conn.close();
            return p;
        } catch (SQLException e) {
            throw new SQLException("Eroo ao recuperar a pessoa. \n" + e.getMessage());
        }
    }
    
    //Comparando idade com a data atual
    public boolean checarNascimento(LocalDate d) {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(d, hoje);
//retornando apenas o ano 
        if (periodo.getYears() < 18) {
            return false;
        } else {
            return true;
        }

    }
    public void cadastrarSemCarro(Object o) throws SQLException {
        Pessoa pessoa = (Pessoa) o;
        Connection conn = null;
        try {
            conn = Conexao.getConexao();

            String sql = "insert into pessoa (nome,cpf,datanasc) "
                    + "values (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            // preenche os valores         
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3, pessoa.getDatanasc().toString());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao tentar cadastrar pessoa sem carro. \n" + e.getMessage());
        }
    }

    @Override
    public void cadastrarMarca() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
