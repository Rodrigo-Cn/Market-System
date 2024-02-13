package br.com.aula.heranca.dao;

import br.com.aula.heranca.conexao.Conexao;
import br.com.aula.heranca.sistema.Produto;
import br.com.aula.heranca.sistema.ProdutoNaoPerecivel;
import br.com.aula.heranca.sistema.ProdutoPerecivel;
import br.com.aula.heranca.sistema.Venda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VendaDao {
    public int retornarCodigo() {
        String buscarVenda = "SELECT codigo FROM venda";
        int codigo = 1;
        try (Statement ps1 = Conexao.getCon().createStatement()) {
            ResultSet resultSet = ps1.executeQuery(buscarVenda);
            while (resultSet.next()) {
                codigo = resultSet.getInt(1)+1;
            }
            return codigo;
        } catch (SQLException e) {
            return 1;
        }
    }
    public void cadastrarVenda(Venda venda, String cpfCliente, int codigoCaixa){

        String sqlCriarVenda = "INSERT INTO venda VALUES (? ,? ,?, ?)";

        PreparedStatement cl = null;

        try {
            cl = Conexao.getCon().prepareStatement(sqlCriarVenda);
            cl.setInt(1,venda.getCodigo());
            cl.setString(2,cpfCliente);
            cl.setInt(3,codigoCaixa);
            cl.setDouble(4,0);
            cl.execute();
            cl.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void inserirTotal(Double total, int codigo){

        String sqlInserirTotal = "UPDATE venda SET total = ? WHERE codigo = ?";

        PreparedStatement cl = null;

        try {
            cl = Conexao.getCon().prepareStatement(sqlInserirTotal);
            cl.setDouble(1,total);
            cl.setInt(2,codigo);
            cl.execute();
            cl.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletarVenda(int codigoVenda){

        String sqlVenda = "DELETE FROM venda WHERE codigo = ?";

        PreparedStatement cl = null;

        try {
            cl = Conexao.getCon().prepareStatement(sqlVenda);
            cl.setInt(1,codigoVenda);
            cl.execute();
            cl.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Venda> ConsultarVendas() {
        String buscarVendas = "SELECT codigo,cpfCliente,total FROM venda";

        try (Statement ps1 = Conexao.getCon().createStatement()) {
            ArrayList<Venda> vendas = new ArrayList<>();
            ResultSet resultSet = ps1.executeQuery(buscarVendas);

            while (resultSet.next()) {
                Venda novaVenda = new Venda(resultSet.getInt(1),resultSet.getDouble(3));
                vendas.add(novaVenda);
            }
            return vendas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
