package service;

import java.util.ArrayList;

import dao.MissaoDAO;
import model.Missao;

public class MissaoService {

    private MissaoDAO dao = new MissaoDAO();

    private void validar(Missao m) throws Exception {
        if (m.getTitulo().trim().isEmpty()) {
            throw new Exception("Preencha o titulo da missao!");
        }
        String r = m.getRankMissao();
        // so pode D, C, B, A ou S
        if (!(r.equals("D") || r.equals("C") || r.equals("B") || r.equals("A") || r.equals("S"))) {
            throw new Exception("Rank da missao invalido! Use D, C, B, A ou S.");
        }
    }

    public void cadastrar(Missao m) throws Exception {
        validar(m);
        dao.inserir(m);
    }

    public void alterar(Missao m) throws Exception {
        if (m.getId() == 0) {
            throw new Exception("Selecione uma missao na tabela primeiro.");
        }
        validar(m);
        dao.alterar(m);
    }

    public void excluir(int id) throws Exception {
        if (id == 0) {
            throw new Exception("Selecione uma missao na tabela primeiro.");
        }
        dao.excluir(id);
    }

    public ArrayList<Missao> listar() throws Exception {
        return dao.buscarTodas();
    }
}
