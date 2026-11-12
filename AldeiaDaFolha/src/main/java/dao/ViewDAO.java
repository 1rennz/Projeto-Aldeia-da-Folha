package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Conexao;

public class ViewDAO {

    private ArrayList<String> colunas = new ArrayList<String>();

    // depois de chamar consultar() da pra pegar o nome das colunas aqui
    public ArrayList<String> getColunas() {
        return colunas;
    }

    // consulta uma das views e devolve as linhas
    public ArrayList<Object[]> consultar(String view) throws SQLException {
        // so deixa consultar as views que a gente criou
        if (!view.equals("vw_ninja_missoes") && !view.equals("vw_total_ninjas_por_vila")
                && !view.equals("vw_total_missoes_por_rank") && !view.equals("vw_missoes_sem_ninjas")) {
            throw new SQLException("View invalida: " + view);
        }

        ArrayList<Object[]> linhas = new ArrayList<Object[]>();
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + view);
        ResultSet rs = stmt.executeQuery();

        ResultSetMetaData meta = rs.getMetaData();
        int qtdColunas = meta.getColumnCount();
        colunas.clear();
        for (int i = 1; i <= qtdColunas; i++) {
            colunas.add(meta.getColumnName(i));
        }

        while (rs.next()) {
            Object[] linha = new Object[qtdColunas];
            for (int i = 0; i < qtdColunas; i++) {
                linha[i] = rs.getObject(i + 1);
            }
            linhas.add(linha);
        }

        rs.close();
        stmt.close();
        conn.close();
        return linhas;
    }
}
