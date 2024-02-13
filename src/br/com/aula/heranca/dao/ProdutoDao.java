package br.com.aula.heranca.dao;

import br.com.aula.heranca.conexao.Conexao;
import br.com.aula.heranca.sistema.Produto;
import br.com.aula.heranca.sistema.ProdutoNaoPerecivel;
import br.com.aula.heranca.sistema.ProdutoPerecivel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoDao {
    public void CadastrarProdutoPerecivel(Produto produto){
        ProdutoPerecivel pp = (ProdutoPerecivel) produto;
        String sqlCriarProdutoPerecivel = "INSERT INTO produto VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getCon().prepareStatement(sqlCriarProdutoPerecivel);
            ps.setInt(1,pp.getCodigo());
            ps.setString(2,pp.getNome());
            ps.setDouble(3,pp.getPreco());
            ps.setInt(4,pp.getQuantEmEstoque());
            ps.setString(5,null);
            ps.setString(6,pp.getDataVencimento());
            ps.setInt(7,0);

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void CadastrarProdutoNaoPerecivel(Produto produto){
        ProdutoNaoPerecivel p = (ProdutoNaoPerecivel) produto;
        String sqlCriarProdutoNaoPerecivel = "INSERT INTO produto VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getCon().prepareStatement(sqlCriarProdutoNaoPerecivel);
            ps.setInt(1,p.getCodigo());
            ps.setString(2,p.getNome());
            ps.setDouble(3,p.getPreco());
            ps.setInt(4,p.getQuantEmEstoque());
            ps.setString(5,p.getMaterial());
            ps.setString(6,null);
            ps.setInt(7,1);


            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Produto> ConsultarProduto() {
        String buscarProduto = "SELECT codigo,nome,preco,quantEmEstoque,material,data_vencimento,tipo FROM produto";

        try (Statement ps1 = Conexao.getCon().createStatement()) {
            ArrayList<Produto> produtos = new ArrayList<>();
            ResultSet resultSet = ps1.executeQuery(buscarProduto);

            while (resultSet.next()) {
                int tipo = resultSet.getInt(7);
                if (tipo==0){
                    ProdutoPerecivel novoProduto = new ProdutoPerecivel(resultSet.getInt(1),resultSet.getString(2), resultSet.getDouble(3),resultSet.getInt(4),resultSet.getString(6));
                    produtos.add(novoProduto);
                }else {
                    ProdutoNaoPerecivel novoProduto = new ProdutoNaoPerecivel(resultSet.getInt(1),resultSet.getString(2), resultSet.getDouble(3),resultSet.getInt(4),resultSet.getString(5));
                    produtos.add(novoProduto);
                }
            }
            return produtos;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int retornarCodigo() {
        String buscarProduto = "SELECT codigo FROM produto";
        int codigo = 1;
        try (Statement ps1 = Conexao.getCon().createStatement()) {
            ResultSet resultSet = ps1.executeQuery(buscarProduto);

            while (resultSet.next()) {
                codigo = resultSet.getInt(1)+1;
            }
            return codigo;
        } catch (SQLException e) {
            return 1;
        }
    }
    public int consultarEstoque(int codigoEstoque) {
        String retornaEstoque = "SELECT quantEmEstoque FROM produto WHERE codigo = ?";
        int estoque = 0;

        try (PreparedStatement ae = Conexao.getCon().prepareStatement(retornaEstoque)) {
            ae.setInt(1, codigoEstoque);
            ResultSet resultSet = ae.executeQuery();

            if (resultSet.next()) {
                estoque = resultSet.getInt("quantEmEstoque");
            }

            return estoque;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public void adicionarEstoque(int codigoEstoque, int quantidadeAdicionar) {

        String inserirEstoque = "UPDATE produto SET quantEmEstoque = ? WHERE codigo = ?";
        int estoque = consultarEstoque(codigoEstoque);
        PreparedStatement ie = null;

        try {
            estoque += quantidadeAdicionar;
            ie = Conexao.getCon().prepareStatement(inserirEstoque);
            ie.setInt(1, estoque);
            ie.setInt(2, codigoEstoque);
            ie.executeUpdate();
            ie.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void retirarEstoque(int codigoEstoque, int estoque) {

        String retirarEstoque = "UPDATE produto SET quantEmEstoque = ? WHERE codigo = ?";
        PreparedStatement ie = null;

        try {
            ie = Conexao.getCon().prepareStatement(retirarEstoque);
            ie.setInt(1, estoque);
            ie.setInt(2, codigoEstoque);
            ie.executeUpdate();
            ie.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
