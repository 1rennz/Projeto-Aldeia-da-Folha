package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Conexao;
import model.Totalizador;

public class TotalizadorDAO {

    // roda um select que volta (nome, quantidade) e joga na lista
    private void contar(Connection conn, String sql, String texto, ArrayList<Totalizador> lista) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            lista.add(new Totalizador(texto + rs.getString(1), rs.getInt(2)));
        }
        rs.close();
        stmt.close();
    }

    public ArrayList<Totalizador> calcular() throws SQLException {
        ArrayList<Totalizador> lista = new ArrayList<Totalizador>();
        Connection conn = Conexao.getConexao();

        contar(conn, "SELECT vila, COUNT(*) FROM tb_ninja GROUP BY vila", "Ninjas por vila - ", lista);
        contar(conn, "SELECT rank_ninja, COUNT(*) FROM tb_ninja GROUP BY rank_ninja", "Ninjas por rank - ", lista);
        contar(conn, "SELECT natureza_chakra, COUNT(*) FROM tb_ninja GROUP BY natureza_chakra", "Ninjas por chakra - ", lista);
        contar(conn, "SELECT rank_missao, COUNT(*) FROM tb_missao GROUP BY rank_missao", "Missoes por rank - ", lista);
        contar(conn, "SELECT status, COUNT(*) FROM tb_missao GROUP BY status", "Missoes por status - ", lista);

        // esses dois sao um numero so
        contar(conn, "SELECT 'total', COUNT(DISTINCT id_ninja) FROM tb_ninja_missao", "Ninjas vinculados a missoes - ", lista);
        contar(conn, "SELECT 'total', COUNT(*) FROM tb_missao WHERE id NOT IN (SELECT id_missao FROM tb_ninja_missao)",
                "Missoes sem ninja - ", lista);

        conn.close();
        return lista;
    }

    public void salvar(ArrayList<Totalizador> lista) throws SQLException {
        Connection conn = Conexao.getConexao();
        String sql = "INSERT INTO tb_totalizador_ninja (descricao, quantidade, data_geracao) VALUES (?, ?, ?)";

        for (Totalizador t : lista) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getDescricao());
            stmt.setInt(2, t.getQuantidade());
            stmt.setTimestamp(3, t.getDataGeracao());
            stmt.executeUpdate();
            stmt.close();
        }

        conn.close();
    }
}
