package br.com.aula.heranca.dao;

import br.com.aula.heranca.conexao.Conexao;
import br.com.aula.heranca.sistema.ItemVenda;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemVendaDao {
    public void cadastrarItemPedido(int codigoVenda, int codigoProduto, ItemVenda itemVenda){

        String sqlItemVenda = "INSERT INTO itemvenda VALUES (? ,? ,?, ?, ?)";

        PreparedStatement cl = null;

        try {
            cl = Conexao.getCon().prepareStatement(sqlItemVenda);
            cl.setInt(1,codigoVenda);
            cl.setInt(2,codigoProduto);
            cl.setInt(3,itemVenda.getQuantidade());
            cl.setDouble(4,itemVenda.getValor());
            cl.setDouble(5,itemVenda.getSubtotal());
            cl.execute();
            cl.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletarItensPedidos(int codigoVenda){

        String sqlItemVenda = "DELETE FROM itemvenda WHERE codigoVenda = ?";

        PreparedStatement cl = null;

        try {
            cl = Conexao.getCon().prepareStatement(sqlItemVenda);
            cl.setInt(1,codigoVenda);
            cl.execute();
            cl.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
