package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Missao;
import service.MissaoService;

public class TelaCadastroMissao extends JFrame {

    private JTextField txtTitulo;
    private JTextArea txtDescricao;
    private JComboBox<String> cbRank;
    private JComboBox<String> cbVila;
    private JComboBox<String> cbStatus;
    private JButton btnSalvar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JTable tblMissoes;
    private DefaultTableModel model;

    // guarda o id da missao clicada na tabela (0 = nenhuma)
    private int idSelecionado = 0;

    private MissaoService service = new MissaoService();

    public TelaCadastroMissao() {
        setTitle("Cadastro de Missões");
        setSize(640, 560);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        criarComponentes();
        carregarTabela();
    }

    private void criarComponentes() {
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 20, 100, 25);
        add(lblTitulo);
        txtTitulo = new JTextField();
        txtTitulo.setBounds(120, 20, 250, 25);
        add(txtTitulo);

        JLabel lblDesc = new JLabel("Descrição:");
        lblDesc.setBounds(20, 55, 100, 25);
        add(lblDesc);
        txtDescricao = new JTextArea();
        txtDescricao.setLineWrap(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescricao);
        scrollDesc.setBounds(120, 55, 250, 70);
        add(scrollDesc);

        JLabel lblRank = new JLabel("Rank:");
        lblRank.setBounds(20, 135, 100, 25);
        add(lblRank);
        cbRank = new JComboBox<String>(new String[] {"D", "C", "B", "A", "S"});
        cbRank.setBounds(120, 135, 250, 25);
        add(cbRank);

        JLabel lblVila = new JLabel("Vila de origem:");
        lblVila.setBounds(20, 170, 100, 25);
        add(lblVila);
        cbVila = new JComboBox<String>(new String[] {"Konoha", "Suna", "Kiri", "Kumo", "Iwa"});
        cbVila.setBounds(120, 170, 250, 25);
        add(cbVila);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(20, 205, 100, 25);
        add(lblStatus);
        cbStatus = new JComboBox<String>(new String[] {"Aberta", "Em andamento", "Concluída", "Cancelada"});
        cbStatus.setBounds(120, 205, 250, 25);
        add(cbStatus);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(400, 20, 120, 30);
        add(btnSalvar);
        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(400, 60, 120, 30);
        add(btnAlterar);
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(400, 100, 120, 30);
        add(btnExcluir);
        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(400, 140, 120, 30);
        add(btnLimpar);

        model = new DefaultTableModel() {
            // nao deixa editar direto na tabela
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Id");
        model.addColumn("Título");
        model.addColumn("Descrição");
        model.addColumn("Rank");
        model.addColumn("Vila");
        model.addColumn("Status");
        tblMissoes = new JTable(model);
        JScrollPane scroll = new JScrollPane(tblMissoes);
        scroll.setBounds(20, 250, 590, 250);
        add(scroll);

        // clicou na linha -> joga os dados nos campos pra alterar/excluir
        tblMissoes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tblMissoes.getSelectedRow();
                if (linha >= 0) {
                    idSelecionado = Integer.parseInt(model.getValueAt(linha, 0).toString());
                    txtTitulo.setText(model.getValueAt(linha, 1).toString());
                    Object desc = model.getValueAt(linha, 2);
                    txtDescricao.setText(desc == null ? "" : desc.toString());
                    cbRank.setSelectedItem(model.getValueAt(linha, 3));
                    cbVila.setSelectedItem(model.getValueAt(linha, 4));
                    cbStatus.setSelectedItem(model.getValueAt(linha, 5));
                }
            }
        });

        btnSalvar.addActionListener(e -> salvar());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limpar());
    }

    private Missao pegarDaTela() {
        Missao m = new Missao();
        m.setId(idSelecionado);
        m.setTitulo(txtTitulo.getText());
        m.setDescricao(txtDescricao.getText());
        m.setRankMissao(cbRank.getSelectedItem().toString());
        m.setVilaOrigem(cbVila.getSelectedItem().toString());
        m.setStatus(cbStatus.getSelectedItem().toString());
        return m;
    }

    private void salvar() {
        try {
            service.cadastrar(pegarDaTela());
            JOptionPane.showMessageDialog(this, "Missão cadastrada!");
            limpar();
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void alterar() {
        try {
            service.alterar(pegarDaTela());
            JOptionPane.showMessageDialog(this, "Missão alterada!");
            limpar();
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void excluir() {
        int resp = JOptionPane.showConfirmDialog(this, "Deseja excluir essa missão?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (resp != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            service.excluir(idSelecionado);
            JOptionPane.showMessageDialog(this, "Missão excluída!");
            limpar();
            carregarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void limpar() {
        idSelecionado = 0;
        txtTitulo.setText("");
        txtDescricao.setText("");
        cbRank.setSelectedIndex(0);
        cbVila.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
        tblMissoes.clearSelection();
    }

    private void carregarTabela() {
        model.setRowCount(0);
        try {
            ArrayList<Missao> missoes = service.listar();
            for (Missao m : missoes) {
                model.addRow(new Object[] {m.getId(), m.getTitulo(), m.getDescricao(),
                        m.getRankMissao(), m.getVilaOrigem(), m.getStatus()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao conectar no banco de dados! " + ex.getMessage());
        }
    }
}
