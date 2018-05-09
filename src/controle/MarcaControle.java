/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.MarcaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import modelo.Marca;

/**
 *
 * @author Rig
 */
public class MarcaControle {
    
    public void gravar(Marca m, JList listaMarcas) throws SQLException{
        if(m.getId()<=0){
            cadastrar(m, listaMarcas);
        }else{
            alterar(m, listaMarcas);
        }
    }
    private void cadastrar(Marca m, JList listaMarcas) throws SQLException{
        MarcaDAO dao = new MarcaDAO();
        dao.cadastrar(m);
        List marcas = dao.listarTodos();
        listaMarcas.setListData(marcas.toArray());
    }
    private void alterar(Marca m, JList listaMarcas) throws SQLException{
        MarcaDAO dao = new MarcaDAO();
        dao.alterar(m);
        List marcas = dao.listarTodos();
        listaMarcas.setListData(marcas.toArray());
    }
    public void atualizarListaCarros(JList listaMarcas) throws SQLException{
        MarcaDAO dao = new MarcaDAO();        
        List marcas= dao.listarTodos();
        listaMarcas.setListData(marcas.toArray());
    }
      public List listarMarcas() throws SQLException {
        List<Marca> lmarcas = new ArrayList<Marca>();  
        MarcaDAO dao = new MarcaDAO();
        lmarcas=dao.listarTodos();
        if(lmarcas==null | lmarcas.size()==0){
            dao.cadastrarMarca();
            lmarcas = dao.listarTodos();
        } return lmarcas;      
    } 
    
}
