/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CarroDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JList;
import modelo.Carro;

/**
 *
 * @author Rig
 */
public class CarroControle {
    
    public void gravar(Carro c, JList listaCarros) throws SQLException{
        if(c.getId()<=0){
            cadastrar(c, listaCarros);
        }else{
            alterar(c, listaCarros);
        }
    }
    public void cadastrar(Carro c, JList listaCarros) throws SQLException{
        CarroDAO dao = new CarroDAO();
        dao.cadastrar(c);
        List carros = dao.listarTodos();
        listaCarros.setListData(carros.toArray());
    }
    private void alterar(Carro c, JList listaCarros) throws SQLException{
        CarroDAO dao = new CarroDAO();
        dao.alterar(c);
        List carros = dao.listarTodos();
        listaCarros.setListData(carros.toArray());
    }
    public void atualizarListaCarros(JList listaCarros) throws SQLException{
        CarroDAO dao = new CarroDAO();        
        List carros= dao.listarTodos();
        listaCarros.setListData(carros.toArray());
    }
    public void excluir(Carro a) throws SQLException{
        CarroDAO dao = new CarroDAO();        
        dao.excluir(a);
    } 
}
