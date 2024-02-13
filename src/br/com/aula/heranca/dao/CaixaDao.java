package br.com.aula.heranca.dao;
import br.com.aula.heranca.conexao.Conexao;
import br.com.aula.heranca.sistema.Caixa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CaixaDao {
    public Caixa ConsultarCaixa() {
        String buscarCaixa = "SELECT codigo, nomeMercado, saldo FROM caixa";

        try (Statement ps1 = Conexao.getCon().createStatement()) {
            ResultSet resultSet = ps1.executeQuery(buscarCaixa);

            if (resultSet.next()) {
                Caixa caixa = new Caixa();
                caixa.setCodigo(resultSet.getInt("codigo"));
                caixa.setNomeMercado(resultSet.getString("nomeMercado"));
                caixa.setSaldo(resultSet.getDouble("saldo"));
                return caixa;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void adicionarSaldo(double saldo) {

        String inserirSaldo = "UPDATE caixa SET saldo = ?";
        PreparedStatement ie = null;

        try {
            ie = Conexao.getCon().prepareStatement(inserirSaldo);
            ie.setDouble(1, saldo);
            ie.executeUpdate();
            ie.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
