package service;

import java.util.ArrayList;

import dao.TotalizadorDAO;
import model.Totalizador;

public class TotalizadorService {

    private TotalizadorDAO dao = new TotalizadorDAO();

    // calcula tudo pelo banco e ja salva na tb_totalizador_ninja
    public ArrayList<Totalizador> gerar() throws Exception {
        ArrayList<Totalizador> lista = dao.calcular();
        dao.salvar(lista);
        return lista;
    }
}
