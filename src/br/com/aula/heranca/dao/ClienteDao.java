package br.com.aula.heranca.dao;

import br.com.aula.heranca.conexao.Conexao;
import br.com.aula.heranca.sistema.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDao {
    public void cadastrarCliente(Cliente cliente){
        String sqlCriarCliente = "INSERT INTO cliente VALUES (?, ?, ?)";

        PreparedStatement cl = null;

        try {
            cl = Conexao.getCon().prepareStatement(sqlCriarCliente);
            cl.setString(1,cliente.getNome());
            cl.setString(2,cliente.getCpf());
            cl.setString(3,cliente.getEndereco());
            cl.execute();
            cl.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Cliente> ConsultarClientes() {

        String buscarCliente = "SELECT nome,cpf,endereco FROM cliente";

        try (Statement ps1 = Conexao.getCon().createStatement()) {

            ArrayList<Cliente> clientes = new ArrayList<>();
            ResultSet resultSet = ps1.executeQuery(buscarCliente);

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                clientes.add(cliente);
            }

            return clientes;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void deletarCliente(Cliente cliente){
        String sqlDeletarCliente = "DELETE FROM cliente WHERE cpf = ?";

        PreparedStatement cl = null;

        try {
            cl = Conexao.getCon().prepareStatement(sqlDeletarCliente);
            cl.setString(1,cliente.getCpf());
            cl.execute();
            cl.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
