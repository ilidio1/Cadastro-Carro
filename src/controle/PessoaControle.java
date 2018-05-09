/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PessoaDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.swing.JList;
import modelo.Pessoa;

/**
 *
 * @author Rig
 */
public class PessoaControle {
    

    public void cadastrar(Pessoa p) throws SQLException{
        PessoaDAO dao = new PessoaDAO();
        dao.cadastrar(p);
   
    }
    private void alterar(Pessoa p, JList listaPessoas) throws SQLException{
        PessoaDAO dao = new PessoaDAO();
        dao.alterar(p);
        List pessoas = dao.listarTodos();
        listaPessoas.setListData(pessoas.toArray());
    }
    public void atualizarListaPessoas(JList listaPessoas) throws SQLException{
        PessoaDAO dao = new PessoaDAO();        
        List pessoas = dao.listarTodos();
        listaPessoas.setListData(pessoas.toArray());
    }
      public boolean checarNascimento(LocalDate d) {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(d, hoje);

        if (periodo.getYears() < 18) {
            return false;
        } else {
            return true;
        }

    }
      
    public void cadastrarSemCarro(Pessoa r) throws SQLException {
        PessoaDAO dao = new PessoaDAO();
        dao.cadastrarSemCarro(r);

    }
     public void excluir(Pessoa a) throws SQLException{
        PessoaDAO dao = new PessoaDAO();        
        dao.excluir(a);
    }

}
