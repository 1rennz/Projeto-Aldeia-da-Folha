package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Conexao;
import model.NinjaMissao;

public class NinjaMissaoDAO {

    public void inserir(NinjaMissao nm) throws SQLException {
        Connection conn = Conexao.getConexao();
        String sql = "INSERT INTO tb_ninja_missao (id_ninja, id_missao, funcao, data_participacao) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, nm.getIdNinja());
        stmt.setInt(2, nm.getIdMissao());
        stmt.setString(3, nm.getFuncao());
        stmt.setDate(4, nm.getDataParticipacao());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    // ve se esse ninja ja ta nessa missao
    public boolean jaVinculado(int idNinja, int idMissao) throws SQLException {
        Connection conn = Conexao.getConexao();

        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM tb_ninja_missao WHERE id_ninja = ? AND id_missao = ?");
        stmt.setInt(1, idNinja);
        stmt.setInt(2, idMissao);
        ResultSet rs = stmt.executeQuery();

        boolean achou = rs.next();

        rs.close();
        stmt.close();
        conn.close();
        return achou;
    }

    public ArrayList<NinjaMissao> buscarTodos() throws SQLException {
        ArrayList<NinjaMissao> lista = new ArrayList<NinjaMissao>();
        Connection conn = Conexao.getConexao();

        String sql = "SELECT nm.id, nm.id_ninja, nm.id_missao, nm.funcao, nm.data_participacao, n.nome, m.titulo "
                + "FROM tb_ninja_missao nm "
                + "INNER JOIN tb_ninja n ON n.id = nm.id_ninja "
                + "INNER JOIN tb_missao m ON m.id = nm.id_missao "
                + "ORDER BY nm.id";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            NinjaMissao nm = new NinjaMissao();
            nm.setId(rs.getInt("id"));
            nm.setIdNinja(rs.getInt("id_ninja"));
            nm.setIdMissao(rs.getInt("id_missao"));
            nm.setFuncao(rs.getString("funcao"));
            nm.setDataParticipacao(rs.getDate("data_participacao"));
            nm.setNomeNinja(rs.getString("nome"));
            nm.setTituloMissao(rs.getString("titulo"));
            lista.add(nm);
        }

        rs.close();
        stmt.close();
        conn.close();
        return lista;
    }
}
