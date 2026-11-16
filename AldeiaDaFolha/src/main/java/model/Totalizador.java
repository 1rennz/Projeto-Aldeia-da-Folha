package model;

import java.sql.Timestamp;

public class Totalizador {

    private int id;
    private String descricao;
    private int quantidade;
    private Timestamp dataGeracao;

    public Totalizador() {
    }

    public Totalizador(String descricao, int quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.dataGeracao = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Timestamp getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Timestamp dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}
