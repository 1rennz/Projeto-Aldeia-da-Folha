package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Conexao;
import model.Missao;

public class MissaoDAO {

    public void inserir(Missao m) throws SQLException {
        Connection conn = Conexao.getConexao();
        String sql = "INSERT INTO tb_missao (titulo, descricao, rank_missao, vila_origem, status) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, m.getTitulo());
        stmt.setString(2, m.getDescricao());
        stmt.setString(3, m.getRankMissao());
        stmt.setString(4, m.getVilaOrigem());
        stmt.setString(5, m.getStatus());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void alterar(Missao m) throws SQLException {
        Connection conn = Conexao.getConexao();
        String sql = "UPDATE tb_missao SET titulo = ?, descricao = ?, rank_missao = ?, vila_origem = ?, status = ? WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, m.getTitulo());
        stmt.setString(2, m.getDescricao());
        stmt.setString(3, m.getRankMissao());
        stmt.setString(4, m.getVilaOrigem());
        stmt.setString(5, m.getStatus());
        stmt.setInt(6, m.getId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void excluir(int id) throws SQLException {
        Connection conn = Conexao.getConexao();

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM tb_missao WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public ArrayList<Missao> buscarTodas() throws SQLException {
        ArrayList<Missao> missoes = new ArrayList<Missao>();
        Connection conn = Conexao.getConexao();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_missao ORDER BY id");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Missao m = new Missao();
            m.setId(rs.getInt("id"));
            m.setTitulo(rs.getString("titulo"));
            m.setDescricao(rs.getString("descricao"));
            m.setRankMissao(rs.getString("rank_missao"));
            m.setVilaOrigem(rs.getString("vila_origem"));
            m.setStatus(rs.getString("status"));
            missoes.add(m);
        }

        rs.close();
        stmt.close();
        conn.close();
        return missoes;
    }
}
