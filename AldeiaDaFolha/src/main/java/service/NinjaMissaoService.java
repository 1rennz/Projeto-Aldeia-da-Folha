package service;

import java.sql.Date;
import java.util.ArrayList;

import dao.NinjaMissaoDAO;
import model.Missao;
import model.Ninja;
import model.NinjaMissao;

public class NinjaMissaoService {

    private NinjaMissaoDAO dao = new NinjaMissaoDAO();

    // REGRA DE NEGOCIO (Q4)
    // Genin  -> D, C
    // Chunin -> D, C, B
    // Jounin -> todas
    // Kage   -> A, S
    public boolean podeParticipar(String rankNinja, String rankMissao) {
        if (rankNinja.equals("Genin")) {
            return rankMissao.equals("D") || rankMissao.equals("C");
        } else if (rankNinja.equals("Chunin")) {
            return rankMissao.equals("D") || rankMissao.equals("C") || rankMissao.equals("B");
        } else if (rankNinja.equals("Jounin")) {
            return true;
        } else if (rankNinja.equals("Kage")) {
            return rankMissao.equals("A") || rankMissao.equals("S");
        }
        return false;
    }

    public void vincular(Ninja ninja, Missao missao, String funcao) throws Exception {
        if (ninja == null || missao == null) {
            throw new Exception("Selecione o ninja e a missao!");
        }

        if (!podeParticipar(ninja.getRankNinja(), missao.getRankMissao())) {
            throw new Exception("O ninja " + ninja.getNome() + " eh " + ninja.getRankNinja()
                    + " e nao pode participar de missao rank " + missao.getRankMissao() + "!");
        }

        if (dao.jaVinculado(ninja.getId(), missao.getId())) {
            throw new Exception("Esse ninja ja esta vinculado nessa missao!");
        }

        NinjaMissao nm = new NinjaMissao();
        nm.setIdNinja(ninja.getId());
        nm.setIdMissao(missao.getId());
        nm.setFuncao(funcao);
        nm.setDataParticipacao(new Date(System.currentTimeMillis()));
        dao.inserir(nm);
    }

    public ArrayList<NinjaMissao> listar() throws Exception {
        return dao.buscarTodos();
    }
}
