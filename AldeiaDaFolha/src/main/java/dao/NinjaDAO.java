package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Conexao;
import model.Ninja;

public class NinjaDAO {

    public void inserir(Ninja ninja) throws SQLException {
        Connection conn = Conexao.getConexao();

        String sql = "INSERT INTO tb_ninja (nome, vila, cla, rank_ninja, natureza_chakra, status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, ninja.getNome());
        stmt.setString(2, ninja.getVila());
        stmt.setString(3, ninja.getCla());
        stmt.setString(4, ninja.getRankNinja());
        stmt.setString(5, ninja.getNaturezaChakra());
        stmt.setString(6, ninja.getStatus());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public ArrayList<Ninja> buscarTodos() throws SQLException {
        ArrayList<Ninja> ninjas = new ArrayList<Ninja>();
        Connection conn = Conexao.getConexao();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_ninja ORDER BY id");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Ninja n = new Ninja();
            n.setId(rs.getInt("id"));
            n.setNome(rs.getString("nome"));
            n.setVila(rs.getString("vila"));
            n.setCla(rs.getString("cla"));
            n.setRankNinja(rs.getString("rank_ninja"));
            n.setNaturezaChakra(rs.getString("natureza_chakra"));
            n.setStatus(rs.getString("status"));
            ninjas.add(n);
        }

        rs.close();
        stmt.close();
        conn.close();
        return ninjas;
    }
}
