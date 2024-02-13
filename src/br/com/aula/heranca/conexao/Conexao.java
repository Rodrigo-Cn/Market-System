package br.com.aula.heranca.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:1234/java_bd";

    /*Eu usei a porta 1234 para conectar o banco, pois a porta genérica
    está no MariaDB.*/
    private static final String user = "root";
    private static final String password = "mariadb";
    private static java.sql.Connection con;
    public static Connection getCon() throws SQLException {
        if (con == null){
            con = DriverManager.getConnection(url,user,password);
        }
        return con;
    }
}
