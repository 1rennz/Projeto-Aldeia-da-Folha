package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaPrincipal extends JFrame {

    private JPanel painel;
    private JLabel lblTitulo;
    private JButton btnNinjas;
    private JButton btnMissoes;
    private JButton btnVincular;
    private JButton btnTotalizadores;
    private JButton btnViews;
    private JButton btnSair;

    public TelaPrincipal() {
        setTitle("Aldeia da Folha - Sistema Ninja");
        setSize(400, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        criarComponentes();
    }

    private void criarComponentes() {
        painel = new JPanel();
        painel.setLayout(null);

        lblTitulo = new JLabel("Gerenciamento Ninja");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(95, 20, 250, 30);
        painel.add(lblTitulo);

        btnNinjas = new JButton("Cadastro de Ninjas");
        btnNinjas.setBounds(100, 70, 200, 35);
        painel.add(btnNinjas);

        btnMissoes = new JButton("Cadastro de Missões");
        btnMissoes.setBounds(100, 115, 200, 35);
        painel.add(btnMissoes);

        btnVincular = new JButton("Vincular Ninja a Missão");
        btnVincular.setBounds(100, 160, 200, 35);
        painel.add(btnVincular);

        btnTotalizadores = new JButton("Totalizadores");
        btnTotalizadores.setBounds(100, 205, 200, 35);
        painel.add(btnTotalizadores);

        btnViews = new JButton("Consultar Views");
        btnViews.setBounds(100, 250, 200, 35);
        painel.add(btnViews);

        btnSair = new JButton("Sair");
        btnSair.setBounds(100, 310, 200, 35);
        painel.add(btnSair);

        // cada botao abre a sua tela
        btnNinjas.addActionListener(e -> new TelaCadastroNinja().setVisible(true));
        btnMissoes.addActionListener(e -> new TelaCadastroMissao().setVisible(true));
        btnVincular.addActionListener(e -> new TelaVincularMissao().setVisible(true));
        btnTotalizadores.addActionListener(e -> new TelaTotalizadores().setVisible(true));
        btnViews.addActionListener(e -> new TelaConsultaViews().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));

        add(painel);
    }
}
