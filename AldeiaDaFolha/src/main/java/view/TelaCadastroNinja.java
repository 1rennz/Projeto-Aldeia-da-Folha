package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Ninja;
import service.NinjaService;

public class TelaCadastroNinja extends JFrame {

    private JTextField txtNome;
    private JComboBox<String> cbVila;
    private JTextField txtCla;
    private JComboBox<String> cbRank;
    private JComboBox<String> cbChakra;
    private JComboBox<String> cbStatus;
    private JButton btnSalvar;
    private JButton btnLimpar;
    private JTable tblNinjas;
    private DefaultTableModel model;

    private NinjaService service = new NinjaService();

    public TelaCadastroNinja() {
        setTitle("Cadastro de Ninjas");
        setSize(620, 520);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        criarComponentes();
        carregarTabela();
    }

    private void criarComponentes() {
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 100, 25);
        add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(130, 20, 200, 25);
        add(txtNome);

        JLabel lblVila = new JLabel("Vila:");
        lblVila.setBounds(20, 55, 100, 25);
        add(lblVila);
        cbVila = new JComboBox<String>(new String[] {"Konoha", "Suna", "Kiri", "Kumo", "Iwa"});
        cbVila.setBounds(130, 55, 200, 25);
        add(cbVila);

        JLabel lblCla = new JLabel("Clã:");
        lblCla.setBounds(20, 90, 100, 25);
        add(lblCla);
        txtCla = new JTextField();
        txtCla.setBounds(130, 90, 200, 25);
        add(txtCla);

        JLabel lblRank = new JLabel("Rank:");
        lblRank.setBounds(20, 125, 100, 25);
        add(lblRank);
        cbRank = new JComboBox<String>(new String[] {"Genin", "Chunin", "Jounin", "Kage"});
        cbRank.setBounds(130, 125, 200, 25);
        add(cbRank);

        JLabel lblChakra = new JLabel("Natureza Chakra:");
        lblChakra.setBounds(20, 160, 110, 25);
        add(lblChakra);
        cbChakra = new JComboBox<String>(new String[] {"Fogo", "Vento", "Raio", "Terra", "Água", "Areia"});
        cbChakra.setBounds(130, 160, 200, 25);
        add(cbChakra);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(20, 195, 100, 25);
        add(lblStatus);
        cbStatus = new JComboBox<String>(new String[] {"Ativo", "Inativo"});
        cbStatus.setBounds(130, 195, 200, 25);
        add(cbStatus);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(370, 20, 120, 30);
        add(btnSalvar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(370, 60, 120, 30);
        add(btnLimpar);

        model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nome");
        model.addColumn("Vila");
        model.addColumn("Clã");
        model.addColumn("Rank");
        model.addColumn("Chakra");
        model.addColumn("Status");
        tblNinjas = new JTable(model);
        JScrollPane scroll = new JScrollPane(tblNinjas);
        scroll.setBounds(20, 240, 570, 220);
        add(scroll);

        btnSalvar.addActionListener(e -> salvar());
        btnLimpar.addActionListener(e -> limpar());
    }

    private void salvar() {
        Ninja n = new Ninja();
        n.setNome(txtNome.getText());
        n.setVila(cbVila.getSelectedItem().toString());
        n.setCla(txtCla.getText());
        n.setRankNinja(cbRank.getSelectedItem().toString());
        n.setNaturezaChakra(cbChakra.getSelectedItem().toString());
        n.setStatus(cbStatus.getSelectedItem().toString());

        try {
            service.cadastrar(n);
            JOptionPane.showMessageDialog(this, "Ninja cadastrado com sucesso!");
            limpar();
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void limpar() {
        txtNome.setText("");
        txtCla.setText("");
        cbVila.setSelectedIndex(0);
        cbRank.setSelectedIndex(0);
        cbChakra.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
    }

    private void carregarTabela() {
        model.setRowCount(0);
        try {
            ArrayList<Ninja> ninjas = service.listar();
            for (Ninja n : ninjas) {
                model.addRow(new Object[] {n.getId(), n.getNome(), n.getVila(), n.getCla(),
                        n.getRankNinja(), n.getNaturezaChakra(), n.getStatus()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao conectar no banco de dados! " + ex.getMessage());
        }
    }
}
