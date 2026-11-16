package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // dados do banco, cada um muda pra sua maquina
    private static String url = "jdbc:postgresql://localhost:5432/aldeia_da_folha";
    private static String usuario = "postgres";
    private static String senha = "admin";

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
}
