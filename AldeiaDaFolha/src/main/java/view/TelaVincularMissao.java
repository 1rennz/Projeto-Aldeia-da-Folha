package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Missao;
import model.Ninja;
import model.NinjaMissao;
import service.MissaoService;
import service.NinjaMissaoService;
import service.NinjaService;

public class TelaVincularMissao extends JFrame {

    private JComboBox<Ninja> cbNinja;
    private JComboBox<Missao> cbMissao;
    private JComboBox<String> cbFuncao;
    private JButton btnVincular;
    private JTable tblVinculos;
    private DefaultTableModel model;

    private NinjaService ninjaService = new NinjaService();
    private MissaoService missaoService = new MissaoService();
    private NinjaMissaoService vinculoService = new NinjaMissaoService();

    public TelaVincularMissao() {
        setTitle("Vincular Ninja a Missão");
        setSize(600, 480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        criarComponentes();
        carregarCombos();
        carregarTabela();
    }

    private void criarComponentes() {
        JLabel lblNinja = new JLabel("Ninja:");
        lblNinja.setBounds(20, 20, 80, 25);
        add(lblNinja);
        cbNinja = new JComboBox<Ninja>();
        cbNinja.setBounds(100, 20, 280, 25);
        add(cbNinja);

        JLabel lblMissao = new JLabel("Missão:");
        lblMissao.setBounds(20, 55, 80, 25);
        add(lblMissao);
        cbMissao = new JComboBox<Missao>();
        cbMissao.setBounds(100, 55, 280, 25);
        add(cbMissao);

        JLabel lblFuncao = new JLabel("Função:");
        lblFuncao.setBounds(20, 90, 80, 25);
        add(lblFuncao);
        cbFuncao = new JComboBox<String>(new String[] {"Líder", "Ataque", "Suporte", "Sensorial", "Médico", "Defesa"});
        cbFuncao.setBounds(100, 90, 280, 25);
        add(cbFuncao);

        btnVincular = new JButton("Vincular");
        btnVincular.setBounds(400, 55, 150, 30);
        add(btnVincular);

        model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Ninja");
        model.addColumn("Missão");
        model.addColumn("Função");
        model.addColumn("Data");
        tblVinculos = new JTable(model);
        JScrollPane scroll = new JScrollPane(tblVinculos);
        scroll.setBounds(20, 140, 550, 280);
        add(scroll);

        btnVincular.addActionListener(e -> vincular());
    }

    private void carregarCombos() {
        try {
            ArrayList<Ninja> ninjas = ninjaService.listar();
            for (Ninja n : ninjas) {
                cbNinja.addItem(n);
            }
            ArrayList<Missao> missoes = missaoService.listar();
            for (Missao m : missoes) {
                cbMissao.addItem(m);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar ninjas/missões: " + ex.getMessage());
        }
    }

    private void vincular() {
        Ninja ninja = (Ninja) cbNinja.getSelectedItem();
        Missao missao = (Missao) cbMissao.getSelectedItem();
        String funcao = cbFuncao.getSelectedItem().toString();

        try {
            // a regra do rank fica no service, se nao puder ele joga exception
            vinculoService.vincular(ninja, missao, funcao);
            JOptionPane.showMessageDialog(this, "Ninja vinculado à missão!");
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Não permitido", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void carregarTabela() {
        model.setRowCount(0);
        try {
            for (NinjaMissao nm : vinculoService.listar()) {
                model.addRow(new Object[] {nm.getId(), nm.getNomeNinja(), nm.getTituloMissao(),
                        nm.getFuncao(), nm.getDataParticipacao()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao conectar no banco de dados! " + ex.getMessage());
        }
    }
}
