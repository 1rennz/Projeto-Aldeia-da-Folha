package service;

import java.util.ArrayList;

import dao.NinjaDAO;
import model.Ninja;

public class NinjaService {

    private NinjaDAO dao = new NinjaDAO();

    public void cadastrar(Ninja ninja) throws Exception {
        if (ninja.getNome().trim().isEmpty()) {
            throw new Exception("Preencha o nome do ninja!");
        }
        if (ninja.getCla().trim().isEmpty()) {
            throw new Exception("Preencha o cla do ninja!");
        }
        dao.inserir(ninja);
    }

    public ArrayList<Ninja> listar() throws Exception {
        return dao.buscarTodos();
    }
}
